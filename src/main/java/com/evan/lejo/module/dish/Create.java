package com.evan.lejo.module.dish;

import com.evan.lejo.api.request.Request;
import com.evan.lejo.model.Dish;
import com.evan.lejo.parameter.DishParameter;
import com.evan.lejo.repository.DishRepository;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class Create implements com.evan.lejo.api.crud.Create< Dish > {

    protected final DishRepository dishRepository;


    public Create( DishRepository dishRepository ) {
        this.dishRepository = dishRepository;
    }


    @Override
    public void create( Request request, Dish dish ) {
        String title       = ( String ) request.getParameter( DishParameter.TITLE );
        String description = ( String ) request.getParameter( DishParameter.DESCRIPTION );
        Double price       = ( Double ) request.getParameter( DishParameter.PRICE );

        dish
                .setTitle( title )
                .setDescription( description )
                .setPrice( price );

        dishRepository.persist( dish );
    }
}
