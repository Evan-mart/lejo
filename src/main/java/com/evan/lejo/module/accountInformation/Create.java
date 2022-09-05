package com.evan.lejo.module.accountInformation;

import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.AccountInformation;
import com.evan.lejo.parameter.AccountInformationParameter;
import com.evan.lejo.repository.AccountInformationRepository;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "createAccountInformation" )
public class Create implements com.evan.lejo.api.crud.Create< AccountInformation > {

    protected final AccountInformationRepository accountInformationRepository;


    public Create( AccountInformationRepository accountInformationRepository ) {
        this.accountInformationRepository = accountInformationRepository;
    }


    @Override
    public void create( Request request, AccountInformation accountInformation ) {
        String email    = ( String ) request.getParameter( AccountInformationParameter.EMAIL );
        String mobile   = ( String ) request.getParameter( AccountInformationParameter.MOBILE );
        String address  = ( String ) request.getParameter( AccountInformationParameter.ADDRESS );
        String city     = ( String ) request.getParameter( AccountInformationParameter.CITY );
        String postCode = ( String ) request.getParameter( AccountInformationParameter.POSTCODE );

        accountInformation
                .setEmail( email )
                .setMobile( mobile )
                .setAddress( address )
                .setCity( city )
                .setPostCode( postCode );

        accountInformationRepository.persist( accountInformation );
    }
}
