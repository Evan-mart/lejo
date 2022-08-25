package com.evan.lejo.repository;

import com.evan.lejo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class OrderRepositoryImpl extends AbstractRepository< Order > implements OrderRepository{

    public OrderRepositoryImpl(
            EntityManager entityManager,
            JpaRepository jpaRepository ) {
        super( entityManager, jpaRepository );
    }


    @Override
    protected Class< Order > getClassType() {
        return null;
    }
}
