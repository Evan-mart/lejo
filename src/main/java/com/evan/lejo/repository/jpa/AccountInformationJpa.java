package com.evan.lejo.repository.jpa;

import com.evan.lejo.model.AccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface AccountInformationJpa extends JpaRepository< AccountInformation, Long > {
}
