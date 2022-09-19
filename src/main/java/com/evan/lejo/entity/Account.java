package com.evan.lejo.entity;

import com.evan.lejo.api.json.annotation.Group;
import com.evan.lejo.api.json.annotation.Json;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.configuration.response.Error;
import com.evan.lejo.exception.HttpUnprocessableEntityException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table( name = "account" )
public class Account {

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    @Column( name = "username", nullable = false )
    private String username;

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    @JsonIgnore
    private String password;

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    @Column( name = "created_at", nullable = false )
    private final ZonedDateTime createdAt;

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    @Column( name = "last_connection", nullable = false )
    private final ZonedDateTime lastConnection;

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "account_information_id", nullable = true )
    private AccountInformation accountInformation;


    public Account() {
        createdAt      = ZonedDateTime.now( ZoneId.of( "UTC" ) );
        lastConnection = ZonedDateTime.now( ZoneId.of( "UTC" ) );
    }


    public long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public Account setUsername( String username ) {
        if ( username == null || username.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_USERNAME_REQUIRED );
        }

        this.username = username;

        return this;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword( String password ) {
        if ( password == null || password.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_PASSWORD_REQUIRED );
        }

        this.password = password;
    }


    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }


    public ZonedDateTime getLastConnection() {
        return lastConnection;
    }


    public AccountInformation getAccountInformation() {
        return accountInformation;
    }


    public void setAccountInformation( AccountInformation accountInformation ) {
        this.accountInformation = accountInformation;
    }
}

























