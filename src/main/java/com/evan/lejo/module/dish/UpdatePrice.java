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
@Service( "updateDishPrice" )
public class UpdatePrice implements Update< Dish > {

    protected final DishRepository dishRepository;


    public UpdatePrice( DishRepository dishRepository ) {
        this.dishRepository = dishRepository;
    }


    @Override
    public void update( Request request, Dish dish ) {
        Double price = ( Double ) request.getParameter( DishParameter.PRICE );

        dish.setPrice( price );

        dishRepository.persist( dish );
    }
}
