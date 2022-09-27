package com.evan.lejo.module.account;

import com.evan.lejo.api.request.MockRequest;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.Account;
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
public class UpdateUsernameTest {
    private UpdateUsername getService() {
        AccountRepository accountRepository = Mockito.mock( AccountRepository.class );

        return new UpdateUsername( accountRepository );
    }


    @Test
    public void success() {
        for ( Request request : db_success() ) {
            Account account = new Account();

            getService().update( request, account );

            Assertions.assertEquals( request.getParameter( AccountParameter.USERNAME ), account.getUsername() );
        }
    }


    public List< Request > db_success() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountParameter.USERNAME, "nouveau nom"
                ) )
        );
    }
}
