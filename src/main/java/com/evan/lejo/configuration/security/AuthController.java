package com.evan.lejo.configuration.security;

import com.evan.lejo.configuration.security.service.UserService;
import com.evan.lejo.configuration.security.user.UserDto;
import com.evan.lejo.entity.Account;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@RestController
@RequestMapping( "/register" )
public class AuthController {
    private final UserService userService;


    public AuthController( UserService userService ) {
        this.userService = userService;
    }


    @PostMapping
    public void registration( @Valid @ModelAttribute( "account" ) UserDto userDto, BindingResult result ) {
        Account existingUser = userService.findAccountByEmail( userDto.getEmail() );

        if ( existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty() ) {
            result.rejectValue( "email", null,
                                "There is already an account registered with the same email" );
        }

        userService.saveAccount( userDto );
    }
}