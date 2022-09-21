package com.evan.lejo.configuration.security.service;

import com.evan.lejo.entity.Account;
import com.evan.lejo.repository.AccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;


    public CustomUserDetailsService( AccountRepository accountRepository ) {
        this.accountRepository = accountRepository;
    }


    @Override
    public UserDetails loadUserByUsername( String email ) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail( email );
        if ( account != null ) {
            return new org.springframework.security.core.userdetails
                    .User( account.getEmail(),
                           account.getPassword(),
                           account.getRoles().stream()
                                  .map( ( role ) -> new SimpleGrantedAuthority( role.getName() ) )
                                  .collect( Collectors.toList() )
            );
        } else {
            throw new UsernameNotFoundException( "Invalid email" );
        }
    }
}
