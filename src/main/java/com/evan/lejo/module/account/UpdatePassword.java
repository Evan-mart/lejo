package com.evan.lejo.module.account;

import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.Account;
import com.evan.lejo.parameter.AccountParameter;
import com.evan.lejo.repository.AccountRepository;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "updateAccountPassword" )
public class UpdatePassword implements Update< Account > {
    protected final AccountRepository accountRepository;


    public UpdatePassword( AccountRepository accountRepository ) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void update( Request request, Account account ) {
        String password = ( String ) request.getParameter( AccountParameter.PASSWORD );

        account.setPassword( password );

        accountRepository.persist( account );
    }
}
