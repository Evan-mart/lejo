package com.evan.lejo.controller.auth;

import com.evan.lejo.api.crud.Create;
import com.evan.lejo.api.json.Encoder;
import com.evan.lejo.api.storage.data.DataStorageHandler;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.configuration.security.AuthRole;
import com.evan.lejo.configuration.security.jwt.JwtUtils;
import com.evan.lejo.configuration.security.request.LoginRequest;
import com.evan.lejo.configuration.security.request.SignupRequest;
import com.evan.lejo.configuration.security.response.JwtResponse;
import com.evan.lejo.configuration.security.response.MessageResponse;
import com.evan.lejo.configuration.security.service.UserDetailsImpl;
import com.evan.lejo.entity.Account;
import com.evan.lejo.entity.Role;
import com.evan.lejo.repository.AccountRepository;
import com.evan.lejo.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController
@RequestMapping( "lejo/auth" )
public class AuthController {
    protected final Create< Account >     createAccount;
    protected final AccountRepository     accountRepository;
    protected final DataStorageHandler    dataStorageHandler;
    protected final RoleRepository        roleRepository;
    protected final PasswordEncoder       passwordEncoder;
    protected final AuthenticationManager authenticationManager;
    protected final JwtUtils              jwtUtils;


    public AuthController(
            Create< Account > createAccount,
            AccountRepository accountRepository,
            DataStorageHandler dataStorageHandler,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils
    ) {
        this.createAccount         = createAccount;
        this.accountRepository     = accountRepository;
        this.dataStorageHandler    = dataStorageHandler;
        this.roleRepository        = roleRepository;
        this.passwordEncoder       = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils              = jwtUtils;
    }


    @Transactional
    @PostMapping( "/signin" )
    public ResponseEntity< ? > authenticateUser( @Valid @RequestBody LoginRequest loginRequest ) {
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
                .ok( jwtResponse );
    }


    @Transactional
    @PostMapping( "/register" )
    public ResponseEntity< Map< String, Object > > registerUser( @Valid @RequestBody SignupRequest signUpRequest ) {

        if ( accountRepository.existsByEmail( signUpRequest.getEmail() ) ) {
            return ResponseEntity
                    .badRequest()
                    .body( Encoder.encode( new MessageResponse( "Erreur: cet email existe déjà !" ), GroupType.ADMIN ) );
        }

        String username = signUpRequest.getUsername();
        String email    = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();

        List< Role > roles     = new ArrayList<>();
        Role         userRoles = roleRepository.findByName( AuthRole.ROLE_USER );
        roles.add( userRoles );

        Account account = new Account();

        account
                .setUsername( username )
                .setEmail( email )
                .setPassword( passwordEncoder.encode( password ) );

        account.setRoles( roles );

        accountRepository.persist( account );

        dataStorageHandler.save();

        return ResponseEntity
                .status( HttpStatus.CREATED )
                .body( Encoder.encode( account, GroupType.ADMIN ) );
    }
}
