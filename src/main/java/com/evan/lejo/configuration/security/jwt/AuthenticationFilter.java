package com.evan.lejo.configuration.security.jwt;

import com.evan.lejo.configuration.security.exception.JwtException;
import com.evan.lejo.configuration.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    protected com.evan.lejo.configuration.security.UserAccessResolver userAccessResolver;

    @Autowired
    protected com.evan.lejo.configuration.security.Security security;


    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain chain ) throws ServletException, IOException {

        try {
            String jwt = parseJwt( request );

            if ( jwt != null && jwtUtils.validateJwtToken( jwt ) ) {
                String username = jwtUtils.getUserNameFromJwtToken( jwt );

                UserDetails userDetails = userDetailsService.loadUserByUsername( username );

                com.evan.lejo.configuration.security.User user = userAccessResolver.getUser( jwt );
                security.setUser( user );

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
