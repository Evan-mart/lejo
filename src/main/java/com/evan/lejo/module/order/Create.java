package com.evan.lejo.module.order;

import com.evan.lejo.api.request.Request;
import com.evan.lejo.model.Order;
import com.evan.lejo.parameter.OrderParameter;
import com.evan.lejo.repository.AccountRepository;
import com.evan.lejo.repository.OrderRepository;
import com.evan.lejo.util.Cast;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "createOrder" )
public class Create implements com.evan.lejo.api.crud.Create< Order > {

    protected final OrderRepository   orderRepository;
    protected final AccountRepository accountRepository;


    public Create(
            OrderRepository orderRepository,
            AccountRepository accountRepository ) {
        this.orderRepository   = orderRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public void create( Request request, Order order ) {
        Long accountId = Cast.getLong( request.getParameter( OrderParameter.ACCOUNT ) );

        order.setAccount( accountRepository.findOrFail( accountId ) );

        orderRepository.persist( order );
    }
}
