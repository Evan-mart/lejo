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
@Service( "updateAccountInformationEmail" )
public class UpdateEmail implements Update< AccountInformation > {

    protected final AccountInformationRepository accountInformationRepository;


    public UpdateEmail( AccountInformationRepository accountInformationRepository ) {
        this.accountInformationRepository = accountInformationRepository;
    }


    @Override
    public void update( Request request, AccountInformation accountInformation ) {
        String email = ( String ) request.getParameter( AccountInformationParameter.EMAIL );

        accountInformation.setEmail( email );

        accountInformationRepository.persist( accountInformation );
    }
}
