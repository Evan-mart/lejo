package com.evan.lejo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
public class ResourceController {

    @GetMapping( "/login" )
    public String loginEndpoint() {
        return "Login!";
    }


    @GetMapping( "/admin" )
    public String adminEndpoint() {
        return "Admin!";
    }


    @GetMapping( "/user" )
    public String userEndpoint() {
        return "User!";
    }


    @GetMapping( "/all" )
    public String allRolesEndpoint() {
        return "All Roles!";
    }


    @DeleteMapping( "/delete" )
    public String deleteEndpoint( @RequestBody String s ) {
        return "I am deleting " + s;
    }
}
