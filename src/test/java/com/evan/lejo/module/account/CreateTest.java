package com.evan.lejo.module.account;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.request.MockRequest;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.model.Account;
import com.evan.lejo.parameter.AccountParameter;
import com.evan.lejo.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class CreateTest {

    private Create< Account > getService() {
        AccountRepository accountRepository = Mockito.mock( AccountRepository.class );

        return new com.evan.lejo.module.account.Create(
                accountRepository
        );
    }


    @Test
    public void test_success() {
        for ( Request request : dpSuccess() ) {
            Account account = new Account();

            getService().create( request, account );

            Assertions.assertEquals( request.getParameter( AccountParameter.USERNAME ), account.getUserName() );
        }
    }


    private List< Request > dpSuccess() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountParameter.USERNAME, "lejo"
                ) )
        );
    }
}
