package com.evan.lejo.module.category;

import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.Category;
import com.evan.lejo.parameter.CategoryParameter;
import com.evan.lejo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "createCategory" )
public class Create implements com.evan.lejo.api.crud.Create< Category > {
    protected final CategoryRepository categoryRepository;


    public Create( CategoryRepository categoryRepository ) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void create( Request request, Category category ) {
        String name = ( String ) request.getParameter( CategoryParameter.NAME );

        category.setName( name );

        categoryRepository.persist( category );
    }
}
