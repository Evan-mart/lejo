package com.evan.lejo.module.account_information;

import com.evan.lejo.api.request.MockRequest;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.AccountInformation;
import com.evan.lejo.module.accountInformation.Create;
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
public class CreateTest {

    public Create getService() {
        AccountInformationRepository accountInformationRepository = Mockito.mock( AccountInformationRepository.class );

        Mockito.when( accountInformationRepository.findOrFail( Mockito.anyLong() ) ).thenReturn( new AccountInformation() );

        return new com.evan.lejo.module.accountInformation.Create( accountInformationRepository );
    }


    @Test
    public void test_success() {
        for ( Request request : dp_success() ) {
            AccountInformation accountInformation = new AccountInformation();

            getService().create( request, accountInformation );

            Assertions.assertEquals( request.getParameter( AccountInformationParameter.MOBILE ), accountInformation.getMobile() );
            Assertions.assertEquals( request.getParameter( AccountInformationParameter.ADDRESS ), accountInformation.getAddress() );
            Assertions.assertEquals( request.getParameter( AccountInformationParameter.CITY ), accountInformation.getCity() );
            Assertions.assertEquals( request.getParameter( AccountInformationParameter.POSTCODE ), accountInformation.getPostCode() );
        }
    }


    @Test
    public void test_fail() {
        for ( Request request : dp_fail() ) {
            Assertions.assertThrows(
                    RuntimeException.class,
                    () -> getService().create( request, new AccountInformation() )
            );
        }
    }


    private List< Request > dp_success() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountInformationParameter.MOBILE, "0601020304",
                        AccountInformationParameter.ADDRESS, "66 rue des loupiots",
                        AccountInformationParameter.POSTCODE, "66699",
                        AccountInformationParameter.CITY, "La Ville"
                ) )
        );
    }


    private List< Request > dp_fail() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountInformationParameter.MOBILE, " "
                ) ),
                MockRequest.build( Map.of(
                        AccountInformationParameter.ADDRESS, ""
                ) ),
                MockRequest.build( Map.of(
                        AccountInformationParameter.POSTCODE, ""
                ) ),
                MockRequest.build( Map.of(
                        AccountInformationParameter.CITY, " "
                ) )
        );
    }
}
