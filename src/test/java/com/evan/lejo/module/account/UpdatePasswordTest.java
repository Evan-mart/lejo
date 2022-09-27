package com.evan.lejo.module.account;

import com.evan.lejo.api.request.MockRequest;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.Account;
import com.evan.lejo.parameter.AccountParameter;
import com.evan.lejo.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class UpdatePasswordTest {

    private UpdatePassword getService() {
        AccountRepository accountRepository = Mockito.mock( AccountRepository.class );
        PasswordEncoder   passwordEncoder   = Mockito.mock( PasswordEncoder.class );

        return new UpdatePassword( accountRepository, passwordEncoder );
    }


    @Test
    public void success() {
        for ( Request request : dp_success() ) {
            Account account = new Account();

            getService().update( request, account );

            Assertions.assertEquals( request.getParameter( AccountParameter.PASSWORD ), account.getPassword() );
        }
    }


    public List< Request > dp_success() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountParameter.PASSWORD, "lkjklkjklkj"
                ) )
        );
    }
}
