package com.evan.lejo.module.category;

import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.Category;
import com.evan.lejo.parameter.CategoryParameter;
import com.evan.lejo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "updateCategoryName" )
public class UpdateName implements Update< Category > {

    protected final CategoryRepository categoryRepository;


    public UpdateName( CategoryRepository categoryRepository ) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void update( Request request, Category category ) {
        String name = ( String ) request.getParameter( CategoryParameter.NAME );

        category.setName( name );

        categoryRepository.persist( category );
    }
}
