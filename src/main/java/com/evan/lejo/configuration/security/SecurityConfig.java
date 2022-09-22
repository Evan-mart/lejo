package com.evan.lejo.configuration.security;

import com.evan.lejo.configuration.security.jwt.AuthEntryPointJwt;
import com.evan.lejo.configuration.security.jwt.AuthTokenFilter;
import com.evan.lejo.configuration.security.jwt.JwtUtils;
import com.evan.lejo.configuration.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt      authEntryPointJwt;


    public SecurityConfig(
            UserDetailsServiceImpl userDetailsService,
            AuthEntryPointJwt authEntryPointJwt,
            JwtUtils jwtUtils
    ) {
        this.userDetailsService = userDetailsService;
        this.authEntryPointJwt  = authEntryPointJwt;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService( userDetailsService );
        authProvider.setPasswordEncoder( passwordEncoder() );

        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration authConfig ) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint( authEntryPointJwt ).and()
            .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
            .authorizeRequests()
            .antMatchers( "/lejo/auth/**" ).permitAll()
            .antMatchers( "/lejo/account-informations/**" ).permitAll()
            .antMatchers( "/lejo/dishes/**" ).permitAll()
            .antMatchers( "/lejo/orders/**" ).permitAll()
            .antMatchers( "/lejo/accounts**" ).permitAll()
            .anyRequest().authenticated();


        http.authenticationProvider( authenticationProvider() );

        http.addFilterBefore( authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class );

        return http.build();
    }
}
