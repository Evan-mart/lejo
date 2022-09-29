package com.evan.lejo.api.security;

import com.evan.lejo.api.security.exception.JwtException;
import com.evan.lejo.configuration.security.jwt.JwtUtils;
import com.evan.lejo.configuration.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
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

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain chain ) throws ServletException, IOException {
        final Optional< String > token = Optional.ofNullable( request.getHeader( HttpHeaders.AUTHORIZATION ) );

        try {
            String jwt = parseJwt( request );

            if ( jwt != null && jwtUtils.validateJwtToken( jwt ) ) {
                String username = jwtUtils.getUserNameFromJwtToken( jwt );

                UserDetails userDetails = userDetailsService.loadUserByUsername( username );

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities() );
                authentication.setDetails( new WebAuthenticationDetailsSource().buildDetails( request ) );

                SecurityContextHolder.getContext().setAuthentication( authentication );
            }
        } catch ( JwtException e ) {

            response.sendError( HttpServletResponse.SC_UNAUTHORIZED, e.getMessage() );
            return;
        }


        chain.doFilter( request, response );

        SecurityContextHolder.getContext().setAuthentication( null );
    }


    private String parseJwt( HttpServletRequest request ) {
        String headerAuth = request.getHeader( "Authorization" );

        if ( StringUtils.hasText( headerAuth ) && headerAuth.startsWith( "Bearer " ) ) {
            return headerAuth.substring( 7, headerAuth.length() );
        }

        return null;
    }
}
