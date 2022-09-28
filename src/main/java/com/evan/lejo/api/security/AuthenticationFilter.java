package com.evan.lejo.api.security;

import com.evan.lejo.api.security.exception.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String                                        BEARER = "Bearer";
    @Autowired
    protected            com.evan.lejo.api.security.UserAccessResolver userAccessResolver;
    @Autowired
    protected            com.evan.lejo.api.security.Security           security;


    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain chain ) throws ServletException, IOException {
        final Optional< String > token = Optional.ofNullable( request.getHeader( HttpHeaders.AUTHORIZATION ) );

        final Authentication authentication;

        if ( token.isPresent()
                && token.get().startsWith( AuthenticationFilter.BEARER ) ) {

            final String bearerToken = token.get().substring( AuthenticationFilter.BEARER.length() + 1 );

            try {
                com.evan.lejo.api.security.User user = userAccessResolver.getUser( bearerToken );
                security.setUser( user );

                authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        user,
                        user.getAuthorities()
                );


                SecurityContextHolder.getContext().setAuthentication( authentication );
            } catch ( JwtException e ) {
                System.out.println( "AuthenticationFilter " + e );
                response.sendError( HttpServletResponse.SC_UNAUTHORIZED, e.getMessage() );
                return;
            }
        }

        chain.doFilter( request, response );

        SecurityContextHolder.getContext().setAuthentication( null );
    }
}
