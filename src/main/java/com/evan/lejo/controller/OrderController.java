package com.evan.lejo.controller;

import com.evan.lejo.exception.ResourceNotFoundException;
import com.evan.lejo.model.Account;
import com.evan.lejo.model.Order;
import com.evan.lejo.repository.AccountRepository;
import com.evan.lejo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    protected final AccountRepository accountRepository;
    protected final OrderRepository orderRepository;

    public OrderController(AccountRepository accountRepository,OrderRepository orderRepository ) {
        this.accountRepository = accountRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/accounts/{accountId}/orders")
    public ResponseEntity<List<Order>>  getAllOrdersByAccountId(@PathVariable("accountId") Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new ResourceNotFoundException("Not found account for id : " + accountId);
        }

        List<Order> orders = orderRepository.findByAccountId(accountId);

        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found order for id : " + id));

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/accounts/{accountId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable(value = "accountId") Long accountId, @RequestBody Order orderRequest) {
        Order order = accountRepository.findById(accountId).map(account -> {
            orderRequest.getAccountId();
            return orderRepository.save(orderRequest);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + accountId));

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
