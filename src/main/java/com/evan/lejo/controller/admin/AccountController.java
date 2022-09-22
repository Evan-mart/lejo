package com.evan.lejo.controller.admin;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.crud.Update;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.api.request.Request;
import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.configuration.security.jwt.JwtUtils;
import com.evan.lejo.entity.Account;
import com.evan.lejo.entity.Order;
import com.evan.lejo.repository.AccountRepository;
import com.evan.lejo.repository.OrderRepository;
import com.evan.lejo.repository.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController
@RequestMapping( "/lejo/accounts" )
public class AccountController {

    protected final Create< Account >     createAccount;
    protected final Update< Account >     updateAccountUsername;
    protected final Update< Account >     updateAccountPassword;
    protected final AccountRepository     accountRepository;
    protected final OrderRepository       orderRepository;
    protected final Request               request;
    protected final DataStorageHandler    dataStorageHandler;
    protected final AuthenticationManager authenticationManager;
    protected final RoleRepository        roleRepository;
    protected final PasswordEncoder       passwordEncoder;
    protected final JwtUtils              jwtUtils;


    public AccountController(
            Create< Account > createAccount,
            Update< Account > updateAccountUsername,
            Update< Account > updateAccountPassword,
            AccountRepository accountRepository,
            OrderRepository orderRepository,
            Request request,
            DataStorageHandler dataStorageHandler,
            AuthenticationManager authenticationManager,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtUtils jwtUtils
    ) {
        this.createAccount         = createAccount;
        this.updateAccountUsername = updateAccountUsername;
        this.updateAccountPassword = updateAccountPassword;
        this.accountRepository     = accountRepository;
        this.orderRepository       = orderRepository;
        this.request               = request;
        this.dataStorageHandler    = dataStorageHandler;
        this.authenticationManager = authenticationManager;
        this.roleRepository        = roleRepository;
        this.passwordEncoder       = passwordEncoder;
        this.jwtUtils              = jwtUtils;
    }


    @GetMapping( "/{id:[0-9]+}" )
    public ResponseEntity< Map< String, Object > > getAccount( @PathVariable( "id" ) long id ) {
        Account account = accountRepository.findOrFail( id );

        return ResponseEntity.ok( Encoder.encode( account, GroupType.ADMIN ) );
    }


    @Transactional
    @GetMapping( "/{id:[0-9]+}/orders" )
    public ResponseEntity< List< Map< String, Object > > > getAllOrdersByAccount( @PathVariable( "id" ) long id ) {
        List< Order > orders = orderRepository.findByAccountId( id );

        return ResponseEntity.ok( Encoder.encode( orders, GroupType.ADMIN ) );
    }


/*    @Transactional
    @PostMapping( "/signin" )
    public ResponseEntity< Map< String, Object > > authenticateUser( @Valid @RequestBody LoginRequest loginRequest ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( loginRequest.getUsername(), loginRequest.getPassword() )
        );

        SecurityContextHolder.getContext().setAuthentication( authentication );

        String jwt = jwtUtils.generateJwtToken( authentication );

        UserDetailsImpl userDetails = ( UserDetailsImpl ) authentication.getPrincipal();

        List< String > roles = userDetails.getAuthorities()
                                          .stream()
                                          .map( item -> item.getAuthority() )
                                          .collect( Collectors.toList() );

        JwtResponse jwtResponse = new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles
        );

        return ResponseEntity
                .status( HttpStatus.OK )
                .body( Encoder.encode( jwtResponse ) );
    }


    @Transactional
    @PostMapping( "/register" )
    public ResponseEntity< Map< String, Object > > create() {
        Account account = new Account();

        createAccount.create( request, account );

        dataStorageHandler.save();

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( Encoder.encode( account, GroupType.ADMIN ) );
    }*/


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/username" )
    public ResponseEntity< Void > updateUsername( @PathVariable( "id" ) long id ) {
        Account account = accountRepository.findOrFail( id );

        updateAccountUsername.update( request, account );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }


    @Transactional
    @PatchMapping( "/{id:[0-9]+}/password" )
    public ResponseEntity< Map< String, Object > > updateAccountPassword( @PathVariable( "id" ) long id ) {
        Account account = accountRepository.findOrFail( id );

        updateAccountPassword.update( request, account );

        dataStorageHandler.save();

        return ResponseEntity.noContent().build();
    }
}





































