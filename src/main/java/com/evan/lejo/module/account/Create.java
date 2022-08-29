package com.evan.lejo.module.account;

import com.evan.lejo.api.request.Request;
import com.evan.lejo.model.Account;
import com.evan.lejo.parameter.AccountParameter;
import com.evan.lejo.repository.AccountRepository;
import org.springframework.stereotype.Service;

import static com.evan.lejo.parameter.AccountParameter.USERNAME;

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
        String username = ( String ) request.getParameter( USERNAME );
        String password = ( String ) request.getParameter( AccountParameter.PASSWORD );

        account
                .setUserName( username )
                .setPassword( password );

        accountRepository.persist( account );
    }
}
