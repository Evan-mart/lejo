package com.evan.lejo.module.account;

import com.evan.lejo.api.request.Request;
import com.evan.lejo.model.Account;
import com.evan.lejo.repository.AccountRepository;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "createAccount" )
public class Create implements com.evan.lejo.api.crud.Create< Account > {

    protected final AccountRepository accountRepository;


    public Create( AccountRepository accountRepository ) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void create( Request request, Account account ) {
        accountRepository.persist( account );
    }
}
