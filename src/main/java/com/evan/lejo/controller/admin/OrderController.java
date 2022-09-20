package com.evan.lejo.controller.admin;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.entity.Orders;
import com.evan.lejo.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController
@RequestMapping( "/orders" )
public class OrderController {

    protected final Create< Orders >   createOrder;
    protected final Update< Orders >   updateStatus;
    protected final OrderRepository    orderRepository;
    protected final Request            request;
    protected final DataStorageHandler dataStorageHandler;


    public OrderController(
            Create< Orders > createOrder,
            Update< Orders > updateStatus,
            OrderRepository orderRepository,
            Request request,
            DataStorageHandler dataStorageHandler ) {
        this.createOrder        = createOrder;
        this.updateStatus       = updateStatus;
        this.orderRepository    = orderRepository;
        this.request            = request;
        this.dataStorageHandler = dataStorageHandler;
    }


    @Transactional
    @GetMapping( "/{id:[0-9]+}" )
    public ResponseEntity< Map< String, Object > > getOrderById( @PathVariable( "id" ) long id ) {
        Orders order = orderRepository.findOrFail( id );

        return ResponseEntity.ok( Encoder.encode( order, GroupType.ADMIN ) );
    }


    @Transactional
    @PostMapping
    public ResponseEntity< Map< String, Object > > create() {
        Orders order = new Orders();

        createOrder.create( request, order );

        dataStorageHandler.save();

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( Encoder.encode( order, GroupType.ADMIN ) );
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/status" )
    public ResponseEntity< Void > updateStatus( @PathVariable( "id" ) long id ) {
        Orders order = orderRepository.findOrFail( id );

        updateStatus.update( request, order );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }
}
