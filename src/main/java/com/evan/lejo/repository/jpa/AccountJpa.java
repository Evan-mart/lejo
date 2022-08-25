package com.evan.lejo.repository.jpa;

import com.evan.lejo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountJpa extends JpaRepository<Account, Long> {
}
