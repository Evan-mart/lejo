package com.evan.lejo.module.accountInformation;

import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.model.AccountInformation;
import com.evan.lejo.parameter.AccountInformationParameter;
import com.evan.lejo.repository.AccountInformationRepository;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "updateAccountInformationAddress" )
public class UpdateAddress implements Update< AccountInformation > {
    protected final AccountInformationRepository accountInformationRepository;


    public UpdateAddress( AccountInformationRepository accountInformationRepository ) {
        this.accountInformationRepository = accountInformationRepository;
    }


    @Override
    public void update( Request request, AccountInformation accountInformation ) {
        String address = ( String ) request.getParameter( AccountInformationParameter.ADDRESS );

        accountInformation.setAddress( address );

        accountInformationRepository.persist( accountInformation );
    }
}
