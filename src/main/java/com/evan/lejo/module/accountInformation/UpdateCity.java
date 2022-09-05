package com.evan.lejo.module.accountInformation;

import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.AccountInformation;
import com.evan.lejo.parameter.AccountInformationParameter;
import com.evan.lejo.repository.AccountInformationRepository;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "updateAccountInformationCity" )
public class UpdateCity implements Update< AccountInformation > {

    protected final AccountInformationRepository accountInformationRepository;


    public UpdateCity( AccountInformationRepository accountInformationRepository ) {
        this.accountInformationRepository = accountInformationRepository;
    }


    @Override
    public void update( Request request, AccountInformation accountInformation ) {
        String city = ( String ) request.getParameter( AccountInformationParameter.CITY );

        accountInformation.setCity( city );

        accountInformationRepository.persist( accountInformation );
    }
}
