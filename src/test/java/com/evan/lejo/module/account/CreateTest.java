package com.evan.lejo.module.account;

import com.evan.lejo.api.request.MockRequest;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.Account;
import com.evan.lejo.parameter.AccountParameter;
import com.evan.lejo.repository.AccountRepository;
import com.evan.lejo.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class CreateTest {

    private Create getService() {
        AccountRepository accountRepository = Mockito.mock( AccountRepository.class );
        RoleRepository    roleRepository    = Mockito.mock( RoleRepository.class );
        PasswordEncoder   passwordEncoder   = Mockito.mock( PasswordEncoder.class );

        Mockito.when( accountRepository.findOrFail( Mockito.anyLong() ) ).thenReturn( new Account() );

        return new com.evan.lejo.module.account.Create(
                accountRepository,
                roleRepository,
                passwordEncoder
        );
    }


    @Test
    public void test_success() {
        for ( Request request : dpSuccess() ) {
            Account account = new Account();

            getService().create( request, account );

            Assertions.assertEquals( request.getParameter( AccountParameter.USERNAME ), account.getUsername() );
            Assertions.assertEquals( request.getParameter( AccountParameter.EMAIL ), account.getEmail() );
            Assertions.assertEquals( request.getParameter( AccountParameter.PASSWORD ), account.getPassword() );
        }
    }


    @Test
    public void test_fail() {
        for ( Request request : dpFail() ) {
            Assertions.assertThrows(
                    RuntimeException.class,
                    () -> getService().create( request, new Account() )
            );
        }
    }


    private List< Request > dpSuccess() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountParameter.USERNAME, "jo",
                        AccountParameter.EMAIL, "lejo@Gmail.com",
                        AccountParameter.PASSWORD, "qwliuhgqrughql254"
                ) )
        );
    }


    private List< Request > dpFail() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountParameter.USERNAME, " "
                ) ),
                MockRequest.build( Map.of(
                        AccountParameter.EMAIL, ""
                ) ),
                MockRequest.build( Map.of(
                        AccountParameter.PASSWORD, ""
                ) )
        );
    }
}
