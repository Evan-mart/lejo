package com.evan.lejo.mock;

import com.evan.lejo.entity.Account;

import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class MockAccount extends Account {
    protected MockAccount() {
        super();
    }


    public static Account build( Map< String, Object > field ) {
        MockAccount account = new MockAccount();

        account.id       = ( long ) field.get( "id" );
        account.username = ( String ) field.get( "username" );
        return account;
    }
}
