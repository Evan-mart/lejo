package com.evan.lejo.module.account_information;

import com.evan.lejo.api.request.MockRequest;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.AccountInformation;
import com.evan.lejo.module.accountInformation.UpdateMobile;
import com.evan.lejo.parameter.AccountInformationParameter;
import com.evan.lejo.repository.AccountInformationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class UpdateMobileTest {
    private UpdateMobile getService() {
        AccountInformationRepository accountInformationRepository = Mockito.mock( AccountInformationRepository.class );

        return new UpdateMobile( accountInformationRepository );
    }


    @Test
    public void success() {
        for ( Request request : db_success() ) {
            AccountInformation accountInformation = new AccountInformation();

            getService().update( request, accountInformation );

            Assertions.assertEquals( request.getParameter( AccountInformationParameter.MOBILE ), accountInformation.getMobile() );
        }
    }


    @Test
    public void fail() {
        for ( Request request : db_fail() ) {
            AccountInformation accountInformation = new AccountInformation();

            Assertions.assertThrows( RuntimeException.class, () -> getService().update( request, accountInformation ) );
        }
    }


    public List< Request > db_success() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountInformationParameter.MOBILE, "0606060606"
                ) )
        );
    }


    public List< Request > db_fail() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountInformationParameter.MOBILE, "0606060606060606060606060606"
                ) ),
                MockRequest.build( Map.of(
                        AccountInformationParameter.MOBILE, ""
                ) )
        );
    }
}
