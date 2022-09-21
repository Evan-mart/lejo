package com.evan.lejo.configuration.security.service;

import com.evan.lejo.configuration.security.user.UserDto;
import com.evan.lejo.entity.Account;

import java.util.List;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public interface UserService {
    void saveAccount( UserDto userDto );


    Account findAccountByEmail( String email );


    List< UserDto > findAllAccounts();
}
