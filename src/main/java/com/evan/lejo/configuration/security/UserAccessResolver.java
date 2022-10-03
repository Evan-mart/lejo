package com.evan.lejo.configuration.security;

import com.evan.lejo.configuration.security.exception.JwtException;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
public interface UserAccessResolver {

    User getUser( String token ) throws JwtException;
}
