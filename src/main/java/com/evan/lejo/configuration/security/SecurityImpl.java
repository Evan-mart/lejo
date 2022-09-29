package com.evan.lejo.configuration.security;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Set;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
@Service
@RequestScope
public class SecurityImpl implements Security {

    protected User user;


    @Override
    public long getId() {
        return user.getId();
    }


    @Override
    public String getUsername() {
        return user.getUsername();
    }


    @Override
    public Set< String > getRoles() {
        return user.getRoles();
    }


    @Override
    public boolean hasRole( final String role ) {
        return user.getRoles().contains( role );
    }


    @Override
    public boolean hasUserConnected() {
        return user != null;
    }


    @Override
    public void setUser( User user ) {
        this.user = user;
    }
}
