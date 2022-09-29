package com.evan.lejo.configuration.security;

import com.evan.lejo.api.json.annotation.Group;
import com.evan.lejo.api.json.annotation.Json;
import com.evan.lejo.exception.HttpUnprocessableEntityException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
public class User {

    private final Set< String > roles;
    @Json( groups = {
            @Group
    } )
    private       long          id;
    @Json( groups = {
            @Group
    } )
    private       String        authenticationId;
    private       String        username;


    public User() {
        roles = new HashSet<>();
    }


    public Long getId() {
        return id;
    }


    public void setId( final long id ) {
        this.id = id;
    }


    public String getAuthenticationId() {
        return authenticationId;
    }


    public void setAuthenticationId( String authenticationId ) {
        if ( authenticationId == null || authenticationId.isEmpty() || authenticationId.isBlank() ) {
            throw new HttpUnprocessableEntityException( "Please, specify a valid authentication id" );
        }

        this.authenticationId = authenticationId;
    }


    public String getUsername() {
        return username;
    }


    public User setUsername( String username ) {
        this.username = username;

        return this;
    }


    public Set< String > getRoles() {
        return roles;
    }


    public void addRole( final String role ) {
        roles.add( role );
    }


    public Collection< ? extends GrantedAuthority > getAuthorities() {
        final Collection< GrantedAuthority > collection = new ArrayList<>();

        roles.forEach( role -> {
            collection.add( new SimpleGrantedAuthority( role ) );
        } );

        return collection;
    }
}
