package com.evan.lejo.configuration.security.service;

import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.configuration.security.AuthRole;
import com.evan.lejo.configuration.security.user.UserDto;
import com.evan.lejo.entity.Account;
import com.evan.lejo.entity.Role;
import com.evan.lejo.repository.AccountRepository;
import com.evan.lejo.repository.RoleRepository;
import com.evan.lejo.repository.jpa.RoleJpa;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Service
public class UserServiceImpl implements UserService {
    private final AccountRepository  accountRepository;
    private final RoleRepository     roleRepository;
    private final RoleJpa            roleJpa;
    private final PasswordEncoder    passwordEncoder;
    private final DataStorageHandler dataStorageHandler;


    public UserServiceImpl(
            AccountRepository accountRepository,
            RoleRepository roleRepository,
            RoleJpa roleJpa,
            PasswordEncoder passwordEncoder,
            DataStorageHandler dataStorageHandler ) {
        this.accountRepository  = accountRepository;
        this.roleRepository     = roleRepository;
        this.roleJpa            = roleJpa;
        this.passwordEncoder    = passwordEncoder;
        this.dataStorageHandler = dataStorageHandler;
    }


    @Override
    public void saveAccount( UserDto userDto ) {
        Account account = new Account();

        account
                .setUsername( userDto.getUsername() )
                .setEmail( userDto.getEmail() )
                .setPassword( passwordEncoder.encode( userDto.getPassword() ) );

        Role role = roleRepository.findByName( AuthRole.ROLE_USER );

        if ( role == null ) {
            role = checkRoleExist();
        }

        account.setRoles( Arrays.asList( role ) );
        accountRepository.persist( account );
        dataStorageHandler.save();
    }


    @Override
    public Account findAccountByEmail( String email ) {
        return accountRepository.findByEmail( email );
    }


    @Override
    public List< UserDto > findAllAccounts() {
        List< Account > accounts = accountRepository.findAll();
        return accounts.stream()
                       .map( ( account ) -> mapToUserDto( account ) )
                       .collect( Collectors.toList() );
    }


    private UserDto mapToUserDto( Account account ) {
        UserDto userDto = new UserDto();

        userDto.setUsername( account.getUsername() );
        userDto.setEmail( account.getEmail() );

        return userDto;
    }


    private Role checkRoleExist() {
        Role role = new Role();
        role.setName( AuthRole.ROLE_USER );
        return roleJpa.save( role );
    }
}
