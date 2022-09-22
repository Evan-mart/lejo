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
        return Account.class;
    }


    @Override
    public Account findByEmail( String email ) {
        return accountJpa.findByEmail( email );
    }


    @Override
    public Account findByUsername( String username ) {
        return accountJpa.findByUsername( username );
    }


    @Override
    public Boolean existsByEmail( String email ) {
        return accountJpa.existsByEmail( email );
    }
}
