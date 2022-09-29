package com.evan.lejo.configuration.security;

import com.evan.lejo.configuration.security.jwt.AuthEntryPointJwt;
import com.evan.lejo.configuration.security.jwt.AuthenticationFilter;
import com.evan.lejo.configuration.security.jwt.JwtUtils;
import com.evan.lejo.configuration.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        http.cors().and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )
            .and()
            .anonymous()
            .and()
            .authorizeRequests()
            .antMatchers( HttpMethod.OPTIONS, "/**" ).permitAll()
            .antMatchers( "/lejo/auth/**" ).permitAll()
            .antMatchers( "/lejo/users/**" ).hasRole( AuthRole.ROLE_USER.replace( "ROLE_", "" ) )
            .antMatchers( "/lejo/admin/**" ).hasRole( AuthRole.ROLE_ADMIN.replace( "ROLE_", "" ) )
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint( authEntryPointJwt );


        http.authenticationProvider( authenticationProvider() );

        http.addFilterBefore( authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class );

        return http.build();
    }


    @Bean
    public AuthenticationFilter authenticationTokenFilterBean() {
        return new AuthenticationFilter();
    }
}
