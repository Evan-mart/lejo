package com.evan.lejo.controller;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.model.Account;
import com.evan.lejo.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController
@RequestMapping( "/accounts" )
public class AccountController {

    protected final Create< Account >  createAccount;
    protected final Update< Account >  updateAccountUsername;
    protected final Update< Account >  updateAccountPassword;
    protected final AccountRepository  accountRepository;
    protected final DataStorageHandler dataStorageHandler;
    protected final Request            request;


    public AccountController(
            Create< Account > createAccount,
            Update< Account > updateAccountUsername,
            Update< Account > updateAccountPassword,
            AccountRepository accountRepository,
            DataStorageHandler dataStorageHandler,
            Request request ) {
        this.createAccount         = createAccount;
        this.updateAccountUsername = updateAccountUsername;
        this.updateAccountPassword = updateAccountPassword;
        this.accountRepository     = accountRepository;
        this.dataStorageHandler    = dataStorageHandler;
        this.request               = request;
    }


    @GetMapping( "/{id:[0-9]+}" )
    public ResponseEntity< Map< String, Object > > getAccount( @PathVariable( "id" ) long id ) {
        Account account = accountRepository.findOrFail( id );

        return ResponseEntity
                .ok( Encoder.encode( account ) );
    }


    @Transactional
    @PostMapping
    public ResponseEntity< Map< String, Object > > create() {
        Account account = new Account();

        createAccount.create( request, account );

        dataStorageHandler.save();

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( Encoder.encode( account ) );
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/username" )
    public ResponseEntity< Map< String, Object > > updateAccountUserName( @PathVariable( "id" ) long id ) {
        Account account = accountRepository.findOrFail( id );

        updateAccountUsername.update( request, account );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/password" )
    public ResponseEntity< Map< String, Object > > updateAccountPassword( @PathVariable( "id" ) long id ) {
        Account account = accountRepository.findOrFail( id );

        updateAccountUsername.update( request, account );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }
}





































