package com.evan.lejo.module.category;

import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.Category;
import com.evan.lejo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service
public class Delete implements com.evan.lejo.api.crud.Delete< Category > {

    protected final CategoryRepository categoryRepository;


    public Delete( CategoryRepository categoryRepository ) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void delete( Request request, Category category ) {
        categoryRepository.remove( category );
    }
}
