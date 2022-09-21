package com.evan.lejo.module.order;

import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.Order;
import com.evan.lejo.parameter.OrderParameter;
import com.evan.lejo.repository.OrderRepository;
import com.evan.lejo.util.Cast;
import org.springframework.stereotype.Service;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service( "updateOrderStatus" )
public class UpdateStatus implements Update< Order > {
    protected final OrderRepository orderRepository;


    public UpdateStatus( OrderRepository orderRepository ) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void update( Request request, Order order ) {
        Byte status = Cast.getByte( request.getParameter( OrderParameter.STATUS ) );

        order.setStatus( status );

        orderRepository.persist( order );
    }
}
