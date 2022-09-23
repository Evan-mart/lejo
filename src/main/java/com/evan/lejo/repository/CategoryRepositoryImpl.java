package com.evan.lejo.repository;

import com.evan.lejo.entity.Category;
import com.evan.lejo.repository.jpa.CategoryJpa;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service
public class CategoryRepositoryImpl extends AbstractRepository< Category > implements CategoryRepository {
    protected final CategoryJpa categoryJpa;


    public CategoryRepositoryImpl(
            EntityManager entityManager,
            CategoryJpa categoryJpa ) {
        super( entityManager, categoryJpa );
        this.categoryJpa = categoryJpa;
    }


    @Override
    protected Class< Category > getClassType() {
        return Category.class;
    }
}
