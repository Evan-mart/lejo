package com.evan.lejo.repository;

import com.evan.lejo.entity.Account;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface AccountRepository extends DefaultRepository< Account > {
    Account findByEmail( String email );


    Account findByUsername( String username );


    Boolean existsByEmail( String email );
}
