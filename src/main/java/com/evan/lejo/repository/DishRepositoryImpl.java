package com.evan.lejo.repository;

import com.evan.lejo.entity.Dish;
import com.evan.lejo.repository.jpa.DishJpa;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service
public class DishRepositoryImpl extends AbstractRepository< Dish > implements DishRepository {

    public DishRepositoryImpl(
            EntityManager entityManager,
            DishJpa dishJpa
    ) {
        super( entityManager, dishJpa );
    }


    @Override
    protected Class< Dish > getClassType() {
        return null;
    }
}
