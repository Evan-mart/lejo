package com.evan.lejo.repository;

import com.evan.lejo.entity.Orders;

import java.util.List;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface OrderRepository extends DefaultRepository< Orders > {

    List< Orders > findByAccountId( long accountId );
}
