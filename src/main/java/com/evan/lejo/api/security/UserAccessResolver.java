package com.evan.lejo.api.security;

import com.evan.lejo.api.security.exception.JwtException;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
public interface UserAccessResolver {

    com.evan.lejo.api.security.User getUser( String token ) throws JwtException;
}
