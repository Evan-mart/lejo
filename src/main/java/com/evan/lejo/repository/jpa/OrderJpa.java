package com.evan.lejo.repository.jpa;

import com.evan.lejo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderJpa extends JpaRepository<Order, Long> {
    List<Order> findByAccountId(Long accountId);
}
