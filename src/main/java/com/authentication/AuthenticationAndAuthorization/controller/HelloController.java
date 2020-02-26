package com.authentication.AuthenticationAndAuthorization.controller;

import com.authentication.AuthenticationAndAuthorization.model.User;
import com.authentication.AuthenticationAndAuthorization.payload.LoginResponse;
import com.authentication.AuthenticationAndAuthorization.repository.UserRepository;
import com.authentication.AuthenticationAndAuthorization.payload.LoginRequest;
import com.authentication.AuthenticationAndAuthorization.service.UserPrincipalDetailService;
import com.authentication.AuthenticationAndAuthorization.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class HelloController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserPrincipalDetailService userPrincipalDetailService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/admin")
    public List<User> helloAdmin()
    {
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public String helloUser()
    {
        return "Hello user";
    }

//    @GetMapping("/public")
//    public String publicHello()
//    {
//        return "hello public resource";
//    }
    @PostMapping("/signup")
    public ResponseEntity createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        try
        {
            authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        }
        catch (BadCredentialsException e)
        {
            throw new Exception("BAD PASSWORD OR USERNAME", e);
        }

        final UserDetails userDetails = userPrincipalDetailService.loadUserByUsername(loginRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(jwt));
    }

}
