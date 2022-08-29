package com.evan.lejo.module.dish;

import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.model.Dish;
import com.evan.lejo.parameter.DishParameter;
import com.evan.lejo.repository.DishRepository;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "updateDIshDescription" )
public class UpdateDescription implements Update< Dish > {

    protected final DishRepository dishRepository;


    public UpdateDescription( DishRepository dishRepository ) {
        this.dishRepository = dishRepository;
    }


    @Override
    public void update( Request request, Dish dish ) {
        String description = ( String ) request.getParameter( DishParameter.DESCRIPTION );

        dish.setDescription( description );

        dishRepository.persist( dish );
    }
}
