package com.evan.lejo.module.account_information;

import com.evan.lejo.api.request.MockRequest;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.AccountInformation;
import com.evan.lejo.module.accountInformation.UpdateAddress;
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
public class UpdateAddressTest {
    private UpdateAddress getService() {
        AccountInformationRepository accountInformationRepository = Mockito.mock( AccountInformationRepository.class );

        return new UpdateAddress( accountInformationRepository );
    }


    @Test
    public void success() {
        for ( Request request : dp_success() ) {
            AccountInformation accountInformation = new AccountInformation();

            getService().update( request, accountInformation );

            Assertions.assertEquals( request.getParameter( AccountInformationParameter.ADDRESS ), accountInformation.getAddress() );
        }
    }


    @Test
    public void fail() {
        for ( Request request : dp_fail() ) {
            AccountInformation accountInformation = new AccountInformation();

            Assertions.assertThrows( RuntimeException.class, () -> getService().update( request, accountInformation ) );
        }
    }


    public List< Request > dp_success() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountInformationParameter.ADDRESS, "95 rue de la pareza"
                ) )
        );
    }


    public List< Request > dp_fail() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountInformationParameter.ADDRESS, ""
                ) )
        );
    }
}
