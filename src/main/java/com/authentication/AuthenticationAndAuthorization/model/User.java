package com.authentication.AuthenticationAndAuthorization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String roles;

    @Column(nullable = false)
    private String authorities;

    public User(String username, String password, String roles, String authorities)
    {
        this.username = username;
        this.roles = roles;
        this.authorities = authorities;
        this.password = password;
    }
    public User()
    {}

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRoles()
    {
        return roles;
    }

    public void setRoles(String roles)
    {
        this.roles = roles;
    }

    public String getAuthorities()
    {
        return authorities;
    }

    public void setAuthorities(String authorities)
    {
        this.authorities = authorities;
    }

    public List<String> getRolesList()
    {
        if(this.roles.length() > 0)
        {
            return Arrays.asList(this.roles.split(","));
        }

        return new ArrayList<>();
    }


    public List<String> getAuthList()
    {
        if(this.authorities.length() > 0)
        {
            return Arrays.asList(this.authorities.split(","));
        }

        return new ArrayList<>();
    }
}
