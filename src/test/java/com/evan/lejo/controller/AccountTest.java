package com.evan.lejo.controller;

import com.evan.lejo.api.container.Container;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.entity.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class AccountTest {

    @BeforeEach
    public void setup() {
        new Encoder( Mockito.mock( Container.class ) );
    }


    @Test
    public void admin() {
        Map< String, Object > encoded = Encoder.encode( new Account(), GroupType.ADMIN );

        Assertions.assertEquals( 6, encoded.size() );

        Assertions.assertTrue( encoded.containsKey( "id" ) );
        Assertions.assertTrue( encoded.containsKey( "username" ) );
        Assertions.assertTrue( encoded.containsKey( "email" ) );
        Assertions.assertTrue( encoded.containsKey( "password" ) );
        Assertions.assertTrue( encoded.containsKey( "created_at" ) );
        Assertions.assertTrue( encoded.containsKey( "last_connection" ) );
    }
}
