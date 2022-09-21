package com.evan.lejo.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers( "/register/**" ).permitAll()
            .antMatchers( "/admin" ).permitAll();
        //.antMatchers( "/admin" ).hasRole( "ADMIN" );

        return http.build();
    }
}
