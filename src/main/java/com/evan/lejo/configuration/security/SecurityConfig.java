package com.evan.lejo.configuration.security;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( securedEnabled = true, prePostEnabled = true )
public class SecurityConfig {

    @Value( "${spring.security.debug:false}" )
    boolean securityDebug;


    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers( HttpMethod.DELETE )
            .hasRole( "ADMIN" )
            .antMatchers( "/admin/**" )
            .hasAnyRole( "ADMIN" )
            .antMatchers( "/user/**" )
            .hasAnyRole( "USER", "ADMIN" )
            .antMatchers( "/login/**" )
            .anonymous()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
            .and()
            .sessionManagement()
            .sessionCreationPolicy( SessionCreationPolicy.STATELESS );

        return http.build();
    }
}
