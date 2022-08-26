package com.evan.lejo.controller;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.model.AccountInformation;
import com.evan.lejo.repository.AccountInformationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController
@RequestMapping( "/account-informations" )
public class AccountInformationController {

    protected final Create< AccountInformation > createAccountInformation;
    protected final Update< AccountInformation > updateAccountInformationEmail;
    protected final Update< AccountInformation > updateAccountInformationMobile;
    protected final Update< AccountInformation > updateAccountInformationAddress;
    protected final Update< AccountInformation > updateAccountInformationCity;
    protected final Update< AccountInformation > updateAccountInformationPostCode;
    protected final AccountInformationRepository accountInformationRepository;
    protected final DataStorageHandler           dataStorageHandler;
    protected final Request                      request;


    public AccountInformationController(
            Create< AccountInformation > createAccountInformation,
            Update< AccountInformation > updateAccountInformationEmail,
            Update< AccountInformation > updateAccountInformationMobile,
            Update< AccountInformation > updateAccountInformationAddress,
            Update< AccountInformation > updateAccountInformationCity,
            Update< AccountInformation > updateAccountInformationPostCode,
            AccountInformationRepository accountInformationRepository,
            DataStorageHandler dataStorageHandler,
            Request request ) {
        this.createAccountInformation         = createAccountInformation;
        this.updateAccountInformationEmail    = updateAccountInformationEmail;
        this.updateAccountInformationMobile   = updateAccountInformationMobile;
        this.updateAccountInformationAddress  = updateAccountInformationAddress;
        this.updateAccountInformationCity     = updateAccountInformationCity;
        this.updateAccountInformationPostCode = updateAccountInformationPostCode;
        this.accountInformationRepository     = accountInformationRepository;
        this.dataStorageHandler               = dataStorageHandler;
        this.request                          = request;
    }


    @GetMapping( "/{id:[0-9]+}" )
    public ResponseEntity< Map< String, Object > > getAccountInformation( @PathVariable( "id" ) long id ) {
        AccountInformation accountInformation = accountInformationRepository.findOrFail( id );

        return ResponseEntity.ok( Encoder.encode( accountInformation ) );
    }


    @Transactional
    @PostMapping
    public ResponseEntity< Map< String, Object > > create() {
        AccountInformation accountInformation = new AccountInformation();

        createAccountInformation.create( request, accountInformation );

        dataStorageHandler.save();

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( Encoder.encode( accountInformation ) );
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/email" )
    public ResponseEntity< Map< String, Object > > updateAccountInformationEmail( @PathVariable( "id" ) long id ) {
        AccountInformation accountInformation = accountInformationRepository.findOrFail( id );

        updateAccountInformationEmail.update( request, accountInformation );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/mobile" )
    public ResponseEntity< Map< String, Object > > updateAccountInformationMobile( @PathVariable( "id" ) long id ) {
        AccountInformation accountInformation = accountInformationRepository.findOrFail( id );

        updateAccountInformationMobile.update( request, accountInformation );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/address" )
    public ResponseEntity< Map< String, Object > > updateAccountInformationAddress( @PathVariable( "id" ) long id ) {
        AccountInformation accountInformation = accountInformationRepository.findOrFail( id );

        updateAccountInformationAddress.update( request, accountInformation );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/city" )
    public ResponseEntity< Map< String, Object > > updateAccountInformationCity( @PathVariable( "id" ) long id ) {
        AccountInformation accountInformation = accountInformationRepository.findOrFail( id );

        updateAccountInformationCity.update( request, accountInformation );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/post-code" )
    public ResponseEntity< Map< String, Object > > updateAccountInformationPostCode( @PathVariable( "id" ) long id ) {
        AccountInformation accountInformation = accountInformationRepository.findOrFail( id );

        updateAccountInformationPostCode.update( request, accountInformation );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }
}
