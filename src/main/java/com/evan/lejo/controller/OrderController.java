package com.evan.lejo.controller;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.entity.Order;
import com.evan.lejo.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController
@RequestMapping( "/orders" )
public class OrderController {

    protected final Create< Order >    createOrder;
    protected final Update< Order >    updateStatus;
    protected final OrderRepository    orderRepository;
    protected final DataStorageHandler dataStorageHandler;
    protected final Request            request;


    public OrderController(
            Create< Order > createOrder,
            Update< Order > updateStatus,
            OrderRepository orderRepository,
            DataStorageHandler dataStorageHandler,
            Request request ) {
        this.createOrder        = createOrder;
        this.updateStatus       = updateStatus;
        this.orderRepository    = orderRepository;
        this.dataStorageHandler = dataStorageHandler;
        this.request            = request;
    }


    @GetMapping
    public ResponseEntity< List< Order > > getAllOrders() {
        List< Order > orders = orderRepository.findAll();

        return ResponseEntity.ok( orders );
    }


    @GetMapping( "/{id:[0-9]+}" )
    public ResponseEntity< Order > getOrder( @PathVariable( "id" ) long id ) {
        Order order = orderRepository.findOrFail( id );

        return ResponseEntity.ok( order );
    }


    @Transactional
    @PostMapping
    public ResponseEntity< Map< String, Object > > create() {
        Order order = new Order();

        createOrder.create( request, order );

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( Encoder.encode( order ) );
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/status" )
    public ResponseEntity< Map< String, Object > > updateStatus( @PathVariable( "id" ) long id ) {
        Order order = orderRepository.findOrFail( id );

        updateStatus.update( request, order );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }
}
