package com.evan.lejo.repository;

import com.evan.lejo.entity.Order;
import com.evan.lejo.repository.jpa.OrderJpa;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service
public class OrderRepositoryImpl extends AbstractRepository< Order > implements OrderRepository {

    protected final OrderJpa orderJpa;


    public OrderRepositoryImpl(
            EntityManager entityManager,
            OrderJpa orderJpa, OrderJpa orderJpa1 ) {
        super( entityManager, orderJpa );
        this.orderJpa = orderJpa1;
    }


    @Override
    protected Class< Order > getClassType() {
        return null;
    }


    @Override
    public List< Order > findByAccountId( long accountId ) {
        return orderJpa.findByAccountId( accountId );
    }
}
