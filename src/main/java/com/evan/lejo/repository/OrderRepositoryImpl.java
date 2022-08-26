package com.evan.lejo.repository;

import com.evan.lejo.model.Order;
import com.evan.lejo.repository.jpa.OrderJpa;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service
public class OrderRepositoryImpl extends AbstractRepository< Order > implements OrderRepository {

    public OrderRepositoryImpl(
            EntityManager entityManager,
            OrderJpa orderJpa ) {
        super( entityManager, orderJpa );
    }


    @Override
    protected Class< Order > getClassType() {
        return null;
    }
}
