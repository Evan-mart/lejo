package com.evan.lejo.repository.jpa;

import com.evan.lejo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpa extends JpaRepository< Account, Long > {
    Account findByEmail( String email );
}
