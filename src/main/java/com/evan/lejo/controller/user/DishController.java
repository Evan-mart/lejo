package com.evan.lejo.controller.user;

import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.entity.Dish;
import com.evan.lejo.repository.DishRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController( "UserDishesController" )
@RequestMapping( "/lejo/user" )
public class DishController {

    protected final DishRepository dishRepository;


    public DishController(
            DishRepository dishRepository
    ) {
        this.dishRepository = dishRepository;
    }


    @Transactional
    @GetMapping( "/dishes/{id:[0-9]+}" )
    public ResponseEntity< Map< String, Object > > getDish( @PathVariable( "id" ) long id ) {
        Dish dish = dishRepository.findOrFail( id );

        return ResponseEntity.ok( Encoder.encode( dish, GroupType.ADMIN ) );
    }
}
