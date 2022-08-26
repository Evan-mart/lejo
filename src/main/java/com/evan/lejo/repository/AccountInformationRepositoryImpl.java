package com.evan.lejo.repository;

import com.evan.lejo.model.AccountInformation;
import com.evan.lejo.repository.jpa.AccountInformationJpa;

import javax.persistence.EntityManager;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class AccountInformationRepositoryImpl extends AbstractRepository< AccountInformation > implements AccountInformationRepository {

    public AccountInformationRepositoryImpl(
            AccountInformationJpa accountInformationJpa,
            EntityManager entityManager
    ) {
        super( entityManager, accountInformationJpa );
    }


    @Override
    protected Class< AccountInformation > getClassType() {
        return null;
    }
}
