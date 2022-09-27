package com.evan.lejo.repository;

import com.evan.lejo.entity.AccountInformation;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface AccountInformationRepository extends DefaultRepository< AccountInformation > {
    AccountInformation findByAccountId( long accountId );
}
