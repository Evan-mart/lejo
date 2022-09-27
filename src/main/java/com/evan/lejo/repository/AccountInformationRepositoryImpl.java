package com.evan.lejo.repository;

import com.evan.lejo.entity.AccountInformation;
import com.evan.lejo.repository.jpa.AccountInformationJpa;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service
public class AccountInformationRepositoryImpl extends AbstractRepository< AccountInformation > implements AccountInformationRepository {
    protected final AccountInformationJpa accountInformationJpa;


    public AccountInformationRepositoryImpl(
            EntityManager entityManager,
            AccountInformationJpa accountInformationJpa ) {
        super( entityManager, accountInformationJpa );
        this.accountInformationJpa = accountInformationJpa;
    }


    @Override
    protected Class< AccountInformation > getClassType() {
        return null;
    }


    @Override
    public AccountInformation findByAccountId( long accountId ) {
        return accountInformationJpa.findByAccountId( accountId );
    }
}
