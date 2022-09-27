package com.evan.lejo.encoder.admin;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.entity.Account;
import com.evan.lejo.entity.AccountInformation;
import com.evan.lejo.repository.AccountInformationRepository;
import com.evan.lejo.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController( "AdminAccountsInformationsController" )
@RequestMapping( "/lejo/admin" )
public class AccountInformationController {

    protected final Create< AccountInformation > createAccountInformation;
    protected final Update< AccountInformation > updateAccountInformationMobile;
    protected final Update< AccountInformation > updateAccountInformationAddress;
    protected final Update< AccountInformation > updateAccountInformationCity;
    protected final Update< AccountInformation > updateAccountInformationPostCode;
    protected final AccountInformationRepository accountInformationRepository;
    protected final AccountRepository            accountRepository;
    protected final DataStorageHandler           dataStorageHandler;
    protected final Request                      request;


    public AccountInformationController(
            Create< AccountInformation > createAccountInformation,
            Update< AccountInformation > updateAccountInformationMobile,
            Update< AccountInformation > updateAccountInformationAddress,
            Update< AccountInformation > updateAccountInformationCity,
            Update< AccountInformation > updateAccountInformationPostCode,
            AccountInformationRepository accountInformationRepository,
            AccountRepository accountRepository,
            DataStorageHandler dataStorageHandler,
            Request request ) {
        this.createAccountInformation         = createAccountInformation;
        this.updateAccountInformationMobile   = updateAccountInformationMobile;
        this.updateAccountInformationAddress  = updateAccountInformationAddress;
        this.updateAccountInformationCity     = updateAccountInformationCity;
        this.updateAccountInformationPostCode = updateAccountInformationPostCode;
        this.accountInformationRepository     = accountInformationRepository;
        this.accountRepository                = accountRepository;
        this.dataStorageHandler               = dataStorageHandler;
        this.request                          = request;
    }


    @GetMapping( "/account_informations/{id:[0-9]+}" )
    public ResponseEntity< Map< String, Object > > getAccountInformation( @PathVariable( "id" ) long id ) {
        AccountInformation accountInformation = accountInformationRepository.findOrFail( id );

        return ResponseEntity.ok( Encoder.encode( accountInformation, GroupType.ADMIN ) );
    }


    @Transactional
    @PostMapping( "/accounts/{id:[0-9]+}/account_informations" )
    public ResponseEntity< Map< String, Object > > create( @PathVariable( "id" ) long id ) {

        Account account = accountRepository.findOrFail( id );

        AccountInformation accountInformation = new AccountInformation();

        accountInformation.setAccount( account );

        createAccountInformation.create( request, accountInformation );

        dataStorageHandler.save();

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( Encoder.encode( accountInformation, GroupType.ADMIN ) );
    }


    @Transactional
    @PatchMapping( "/account_informations/{id:[0-9]+}/mobile" )
    public ResponseEntity< Map< String, Object > > updateAccountInformationMobile( @PathVariable( "id" ) long id ) {
        AccountInformation accountInformation = accountInformationRepository.findOrFail( id );

        updateAccountInformationMobile.update( request, accountInformation );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/account_informations/{id:[0-9]+}/address" )
    public ResponseEntity< Map< String, Object > > updateAccountInformationAddress( @PathVariable( "id" ) long id ) {
        AccountInformation accountInformation = accountInformationRepository.findOrFail( id );

        updateAccountInformationAddress.update( request, accountInformation );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/account_informations/{id:[0-9]+}/city" )
    public ResponseEntity< Map< String, Object > > updateAccountInformationCity( @PathVariable( "id" ) long id ) {
        AccountInformation accountInformation = accountInformationRepository.findOrFail( id );

        updateAccountInformationCity.update( request, accountInformation );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/account_informations/{id:[0-9]+}/post_code" )
    public ResponseEntity< Map< String, Object > > updateAccountInformationPostCode( @PathVariable( "id" ) long id ) {
        AccountInformation accountInformation = accountInformationRepository.findOrFail( id );

        updateAccountInformationPostCode.update( request, accountInformation );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }
}
