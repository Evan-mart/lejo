package com.evan.lejo.encoder;

import com.evan.lejo.api.container.Container;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.entity.AccountInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class AccountInformationTest {

    @BeforeEach
    public void setup() {
        new Encoder( Mockito.mock( Container.class ) );
    }


    @Test
    public void admin() {
        Map< String, Object > encoded = Encoder.encode( new AccountInformation(), GroupType.ADMIN );

        Assertions.assertEquals( 6, encoded.size() );

        Assertions.assertTrue( encoded.containsKey( "id" ) );
        Assertions.assertTrue( encoded.containsKey( "mobile" ) );
        Assertions.assertTrue( encoded.containsKey( "address" ) );
        Assertions.assertTrue( encoded.containsKey( "city" ) );
        Assertions.assertTrue( encoded.containsKey( "post_code" ) );
        Assertions.assertTrue( encoded.containsKey( "account_id" ) );
    }
}
