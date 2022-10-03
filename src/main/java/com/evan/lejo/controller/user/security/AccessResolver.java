package com.evan.lejo.controller.user.security;

import com.evan.lejo.entity.Account;
import com.evan.lejo.entity.Order;
import com.evan.lejo.repository.AccountRepository;
import com.evan.lejo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Romain Lavabre <romainlavabre98@gmail.com>
 */
@Service( "AccountAccessResolver" )
public class AccessResolver {
    protected final AccountRepository accountRepository;
    protected final OrderRepository   orderRepository;


    public AccessResolver(
            AccountRepository accountRepository,
            OrderRepository orderRepository
    ) {
        this.accountRepository = accountRepository;
        this.orderRepository   = orderRepository;
    }


    boolean hasAccess( Order order, Long account_id ) {
        System.out.println( "account id :" + account_id );
        Account account = accountRepository.findOrFail( account_id );

        List< Order > orders = orderRepository.findByAccountId( account_id );

        for ( Order orderAccount : orders ) {
            if ( orderAccount.getAccount().getId() == order.getId() ) {
                return true;
            }
        }

        return false;
    }
}
