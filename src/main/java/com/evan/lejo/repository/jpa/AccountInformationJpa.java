package com.evan.lejo.repository.jpa;

import com.evan.lejo.entity.AccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Repository
public interface AccountInformationJpa extends JpaRepository< AccountInformation, Long > {
    AccountInformation findByAccountId( long accountId );
}
