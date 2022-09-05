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
@Service( "updateAccountInformationMobile" )
public class UpdateMobile implements Update< AccountInformation > {

    protected final AccountInformationRepository accountInformationRepository;


    public UpdateMobile( AccountInformationRepository accountInformationRepository ) {
        this.accountInformationRepository = accountInformationRepository;
    }


    @Override
    public void update( Request request, AccountInformation accountInformation ) {
        String mobile = ( String ) request.getParameter( AccountInformationParameter.MOBILE );

        accountInformation.setMobile( mobile );

        accountInformationRepository.persist( accountInformation );
    }
}
