package com.evan.lejo.encoder;

import com.evan.lejo.api.container.Container;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class CategoryTest {

    @BeforeEach
    public void setup() {
        new Encoder( Mockito.mock( Container.class ) );
    }


    @Test
    public void admin() {
        Map< String, Object > encoded = Encoder.encode( new Category(), GroupType.ADMIN );

        Assertions.assertEquals( 2, encoded.size() );

        Assertions.assertTrue( encoded.containsKey( "id" ) );
        Assertions.assertTrue( encoded.containsKey( "name" ) );
    }
}
