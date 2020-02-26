package com.authentication.AuthenticationAndAuthorization.service;

import com.authentication.AuthenticationAndAuthorization.model.User;
import com.authentication.AuthenticationAndAuthorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DatabaseLoader implements CommandLineRunner
{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception
    {
        User auga = new User("auga", passwordEncoder.encode("auga123"), "ADMIN, USER", "VIEW_ALL" );
        User nel = new User("nel", passwordEncoder.encode("nel123"), "USER", "VIEW_USER" );
        User joe = new User("joe", passwordEncoder.encode("joe123"), "USER", "VIEW_USER" );
        User salo = new User("salo", passwordEncoder.encode("salo123"), "USER", "VIEW_USER" );

        List<User> users = Arrays.asList(auga, nel, joe, salo);
        userRepository.saveAll(users);
    }
}
