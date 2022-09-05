package com.evan.lejo.module.dish;

import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.Dish;
import com.evan.lejo.parameter.DishParameter;
import com.evan.lejo.repository.DishRepository;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "updateDishTitle" )
public class UpdateTitle implements Update< Dish > {
    protected final DishRepository dishRepository;


    public UpdateTitle( DishRepository dishRepository ) {
        this.dishRepository = dishRepository;
    }


    @Override
    public void update( Request request, Dish dish ) {
        String title = ( String ) request.getParameter( DishParameter.TITLE );

        dish.setTitle( title );

        dishRepository.persist( dish );
    }
}
