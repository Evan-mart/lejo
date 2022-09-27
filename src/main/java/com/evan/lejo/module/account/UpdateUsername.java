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
@Service( "updateAccountUsername" )
public class UpdateUsername implements Update< Account > {

    protected final AccountRepository accountRepository;


    public UpdateUsername( AccountRepository accountRepository ) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void update( Request request, Account account ) {
        String username = ( String ) request.getParameter( AccountParameter.USERNAME );

        account.setUsername( username );

        accountRepository.persist( account );
    }
}
