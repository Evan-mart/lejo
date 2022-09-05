package com.evan.lejo.repository.jpa;

import com.evan.lejo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpa extends JpaRepository< Order, Long > {

    List< Order > findByAccountId( long accountId );
}
