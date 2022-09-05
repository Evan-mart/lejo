package com.evan.lejo.repository;

import com.evan.lejo.entity.Order;

import java.util.List;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface OrderRepository extends DefaultRepository< Order > {

    List< Order > findByAccountId( long accountId );
}
