package com.evan.lejo.module.dish;

import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.Dish;
import com.evan.lejo.parameter.DishParameter;
import com.evan.lejo.repository.DishRepository;
import com.evan.lejo.util.Cast;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "createDish" )
public class Create implements com.evan.lejo.api.crud.Create< Dish > {

    protected final DishRepository dishRepository;


    public Create( DishRepository dishRepository ) {
        this.dishRepository = dishRepository;
    }


    @Override
    public void create( Request request, Dish dish ) {
        String title       = ( String ) request.getParameter( DishParameter.TITLE );
        String description = ( String ) request.getParameter( DishParameter.DESCRIPTION );
        Double price       = Cast.getDouble( request.getParameter( DishParameter.PRICE ) );

        dish
                .setTitle( title )
                .setDescription( description )
                .setPrice( price );

        dishRepository.persist( dish );
    }
}
