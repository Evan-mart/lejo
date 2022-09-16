package com.evan.lejo.repository;

import com.evan.lejo.entity.Account;
import com.evan.lejo.repository.jpa.AccountJpa;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service
public class AccountRepositoryImpl extends AbstractRepository< Account > implements AccountRepository {

    protected final AccountJpa accountJpa;


    public AccountRepositoryImpl(
            EntityManager entityManager,
            AccountJpa accountJpa
    ) {
        super( entityManager, accountJpa );
        this.accountJpa = accountJpa;
    }


    @Override
    protected Class< Account > getClassType() {
        return null;
    }
}
