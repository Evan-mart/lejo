package com.evan.lejo.module.account;

import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.Account;
import com.evan.lejo.entity.Role;
import com.evan.lejo.repository.AccountRepository;
import com.evan.lejo.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;


/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "createAccount" )
public class Create implements com.evan.lejo.api.crud.Create< Account > {

    protected final AccountRepository accountRepository;
    protected final RoleRepository    roleRepository;
    protected final PasswordEncoder   encoder;


    public Create(
            AccountRepository accountRepository,
            RoleRepository roleRepository,
            PasswordEncoder encoder
    ) {
        this.accountRepository = accountRepository;
        this.roleRepository    = roleRepository;
        this.encoder           = encoder;
    }


    @Override
    public void create( Request request, Account account ) {
        String username = ( String ) request.getParameter( "username" );
        String email    = ( String ) request.getParameter( "email" );
        String password = ( String ) request.getParameter( "password" );

        account
                .setUsername( username )
                .setEmail( email )
                .setPassword( encoder.encode( password ) );

        Role role = roleRepository.findByName( "ROLE_ADMIN" );

        account.setRoles( Arrays.asList( role ) );

        accountRepository.persist( account );
    }
}
