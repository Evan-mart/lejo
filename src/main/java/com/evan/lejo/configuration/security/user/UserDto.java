package com.evan.lejo.configuration.security.user;

import javax.validation.constraints.NotEmpty;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class UserDto {
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty( message = "Email should not be empty" )
    private String email;

    @NotEmpty( message = "Password should be empty" )
    private String password;


    public Long getId() {
        return id;
    }


    public UserDto setId( Long id ) {
        this.id = id;

        return this;
    }


    public String getUsername() {
        return username;
    }


    public UserDto setUsername( String username ) {
        this.username = username;

        return this;
    }


    public String getEmail() {
        return email;
    }


    public UserDto setEmail( String email ) {
        this.email = email;

        return this;
    }


    public String getPassword() {
        return password;
    }


    public UserDto setPassword( String password ) {
        this.password = password;

        return this;
    }
}
