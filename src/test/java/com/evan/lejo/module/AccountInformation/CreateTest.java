package com.evan.lejo.module.AccountInformation;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.request.MockRequest;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.AccountInformation;
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

    private Create< AccountInformation > getService() {
        AccountInformationRepository accountInformationRepository = Mockito.mock( AccountInformationRepository.class );

        return new com.evan.lejo.module.accountInformation.Create(
                accountInformationRepository
        );
    }


    @Test
    public void test_success() {
        for ( Request request : dpSuccess() ) {
            AccountInformation accountInformation = new AccountInformation();

            getService().create( request, accountInformation );

            Assertions.assertNotNull( request.getParameter( AccountInformationParameter.MOBILE ), accountInformation.getMobile() );
            Assertions.assertNotNull( request.getParameter( AccountInformationParameter.ADDRESS ), accountInformation.getAddress() );
            Assertions.assertNotNull( request.getParameter( AccountInformationParameter.CITY ), accountInformation.getCity() );
            Assertions.assertNotNull( request.getParameter( AccountInformationParameter.POSTCODE ), accountInformation.getPostCode() );
        }
    }


    @Test
    public void test_fail() {
        for ( Request request : dpFail() ) {
            Assertions.assertThrows(
                    RuntimeException.class,
                    () -> getService().create( request, new AccountInformation() )
            );
        }
    }


    private List< Request > dpSuccess() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountInformationParameter.EMAIL, "jeanjean@gmil.com",
                        AccountInformationParameter.MOBILE, "0601020304",
                        AccountInformationParameter.ADDRESS, "55 reo di la",
                        AccountInformationParameter.CITY, "santiago",
                        AccountInformationParameter.POSTCODE, "55000"
                ) )
        );
    }


    private List< Request > dpFail() {
        return List.of(
                MockRequest.build( Map.of(
                        AccountInformationParameter.EMAIL, ""
                ) ),
                MockRequest.build( Map.of(
                        AccountInformationParameter.MOBILE, ""
                ) ),
                MockRequest.build( Map.of(
                        AccountInformationParameter.ADDRESS, ""
                ) ),
                MockRequest.build( Map.of(
                        AccountInformationParameter.CITY, ""
                ) ),
                MockRequest.build( Map.of(
                        AccountInformationParameter.POSTCODE, ""
                ) ),
                MockRequest.build( Map.of(
                        AccountInformationParameter.EMAIL, "jeanjean@gmil.com",
                        AccountInformationParameter.ADDRESS, "55 reo di la",
                        AccountInformationParameter.CITY, "santiago",
                        AccountInformationParameter.POSTCODE, "55000"
                ) )
        );
    }
}
