package com.evan.lejo.configuration.security.service;

import com.evan.lejo.entity.Account;
import com.evan.lejo.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;


    public UserDetailsServiceImpl( AccountRepository accountRepository ) {
        this.accountRepository = accountRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername( username );

        if ( account == null ) {
            throw new UsernameNotFoundException( "Utilisateur non trouvé" );
        }
        return UserDetailsImpl.build( account );
    }
}
