package com.evan.lejo.controller.user;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.controller.user.security.ResourceSecurity;
import com.evan.lejo.entity.Order;
import com.evan.lejo.repository.AccountRepository;
import com.evan.lejo.repository.DishRepository;
import com.evan.lejo.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController( "UserOrdersController" )
@RequestMapping( "/lejo/users" )
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class OrderController {

    protected final Create< Order >    createOrder;
    protected final Update< Order >    updateStatus;
    protected final AccountRepository  accountRepository;
    protected final OrderRepository    orderRepository;
    protected final DishRepository     dishRepository;
    protected final Request            request;
    protected final DataStorageHandler dataStorageHandler;


    public OrderController(
            Create< Order > createOrder,
            Update< Order > updateStatus,
            AccountRepository accountRepository,
            OrderRepository orderRepository,
            DishRepository dishRepository,
            Request request,
            DataStorageHandler dataStorageHandler ) {
        this.createOrder        = createOrder;
        this.updateStatus       = updateStatus;
        this.accountRepository  = accountRepository;
        this.orderRepository    = orderRepository;
        this.dishRepository     = dishRepository;
        this.request            = request;
        this.dataStorageHandler = dataStorageHandler;
    }


    @Transactional
    @GetMapping( "/orders/{id:[0-9]+}" )
    public ResponseEntity< Map< String, Object > > getOrderById( @PathVariable( "id" ) long id ) {
        Order order = orderRepository.findOrFail( id );

        ResourceSecurity.assertAccessAllowed( order );

        return ResponseEntity.ok( Encoder.encode( order, GroupType.USER ) );
    }


    @Transactional
    @PostMapping( "/orders" )
    public ResponseEntity< Map< String, Object > > create() {
        Order order = new Order();

        createOrder.create( request, order );

        dataStorageHandler.save();

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( Encoder.encode( order, GroupType.ADMIN ) );
    }


    @Transactional
    @PatchMapping( "/orders/{id:[0-9]+}/status" )
    public ResponseEntity< Void > updateStatus( @PathVariable( "id" ) long id ) {
        Order order = orderRepository.findOrFail( id );

        updateStatus.update( request, order );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }
}
