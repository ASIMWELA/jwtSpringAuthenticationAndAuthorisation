package com.authentication.AuthenticationAndAuthorization.service;

import com.authentication.AuthenticationAndAuthorization.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sun.java2d.windows.GDIRenderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails
{
    private User user;

    public UserPrincipal(User user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> gAuth = new ArrayList<>();

        this.user.getAuthList().forEach(p->{
            GrantedAuthority au = new SimpleGrantedAuthority(p);
            gAuth.add(au);
        });

        this.user.getRolesList().forEach(p->{
            GrantedAuthority au = new SimpleGrantedAuthority("ROLE_" + p);
            gAuth.add(au);
        });

        return gAuth;
    }

    @Override
    public String getPassword()
    {
        return this.user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
