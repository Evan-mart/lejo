package com.evan.lejo.encoder;

import com.evan.lejo.api.container.Container;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.entity.Dish;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class DishTest {

    @BeforeEach
    public void setup() {
        new Encoder( Mockito.mock( Container.class ) );
    }


    @Test
    public void admin() {
        Map< String, Object > encoded = Encoder.encode( new Dish(), GroupType.ADMIN );

        Assertions.assertEquals( 5, encoded.size() );

        Assertions.assertTrue( encoded.containsKey( "id" ) );
        Assertions.assertTrue( encoded.containsKey( "title" ) );
        Assertions.assertTrue( encoded.containsKey( "description" ) );
        Assertions.assertTrue( encoded.containsKey( "price" ) );
        Assertions.assertTrue( encoded.containsKey( "category_id" ) );
    }
}
