package com.evan.lejo.controller;

import com.evan.lejo.api.request.Request;
import com.evan.lejo.entity.Order;
import com.evan.lejo.repository.AccountRepository;
import com.evan.lejo.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController
@RequestMapping( "/accounts" )
public class AccountOrderController {

    protected final OrderRepository   orderRepository;
    protected final AccountRepository accountRepository;
    protected final Request           request;


    public AccountOrderController( OrderRepository orderRepository, AccountRepository accountRepository, Request request ) {
        this.orderRepository   = orderRepository;
        this.accountRepository = accountRepository;
        this.request           = request;
    }


    @GetMapping( "/{id:[0-9]+}/orders" )
    public ResponseEntity< List< Order > > getAccountOrders( @PathVariable( "id" ) long id ) {
        List< Order > orders = orderRepository.findByAccountId( id );

        return ResponseEntity.ok( orders );
    }
}

