package com.evan.lejo.configuration.security;

import java.util.Set;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
public interface Security {
    long getId();


    String getUsername();


    Set< String > getRoles();


    boolean hasRole( String role );


    boolean hasUserConnected();


    void setUser( User user );
}
