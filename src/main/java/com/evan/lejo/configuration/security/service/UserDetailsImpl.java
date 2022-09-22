package com.evan.lejo.configuration.security.service;

import com.evan.lejo.api.json.annotation.Group;
import com.evan.lejo.api.json.annotation.Json;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    private final Long id;

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    private final String username;

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    private final String email;

    @JsonIgnore
    private final String password;

    public Collection< ? extends GrantedAuthority > authorities;


    public UserDetailsImpl( Long id, String username, String email, String password,
                            Collection< ? extends GrantedAuthority > authorities ) {
        this.id          = id;
        this.username    = username;
        this.email       = email;
        this.password    = password;
        this.authorities = authorities;
    }


    public static UserDetailsImpl build( Account user ) {
        List< GrantedAuthority > authorities = user.getRoles()
                                                   .stream()
                                                   .map( role -> new SimpleGrantedAuthority( role.getName() ) )
                                                   .collect( Collectors.toList() );

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities );
    }


    @Override
    public Collection< ? extends GrantedAuthority > getAuthorities() {
        return authorities;
    }


    public Long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }


    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        UserDetailsImpl user = ( UserDetailsImpl ) o;
        return Objects.equals( id, user.id );
    }
}
