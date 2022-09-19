package com.evan.lejo.repository;

import com.evan.lejo.entity.Orders;
import com.evan.lejo.repository.jpa.OrderJpa;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service
public class OrderRepositoryImpl extends AbstractRepository< Orders > implements OrderRepository {

    protected final OrderJpa orderJpa;


    public OrderRepositoryImpl(
            EntityManager entityManager,
            OrderJpa orderJpa ) {
        super( entityManager, orderJpa );
        this.orderJpa = orderJpa;
    }


    @Override
    protected Class< Orders > getClassType() {
        return Orders.class;
    }


    @Override
    public List< Orders > findByAccountId( long accountId ) {
        return orderJpa.findByAccountId( accountId );
    }
}
