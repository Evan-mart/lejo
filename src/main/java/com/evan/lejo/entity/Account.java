package com.evan.lejo.entity;

import com.evan.lejo.configuration.response.Error;
import com.evan.lejo.exception.HttpUnprocessableEntityException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "account" )
public class Account {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Column( name = "username", nullable = false )
    private String username;

    @JsonIgnore
    private String password;

    @Column( name = "created_at", nullable = false )
    private final ZonedDateTime createdAt;

    @Column( name = "last_connection", nullable = false )
    private final ZonedDateTime lastConnection;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "account_information_id", nullable = false )
    private AccountInformation accountInformation;

    @OneToMany( mappedBy = "account" )
    private final List< Order > orders;


    public Account() {
        createdAt      = ZonedDateTime.now( ZoneId.of( "UTC" ) );
        lastConnection = ZonedDateTime.now( ZoneId.of( "UTC" ) );
        orders         = new ArrayList<>();
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


    public List< Order > getOrders() {
        return orders;
    }


    public Account addOrder( Order order ) {
        if ( this.orders.contains( order ) ) {
            return this;
        }

        this.orders.add( order );

        if ( order.getAccount() != this ) {
            order.setAccount( this );
        }

        return this;
    }
}

























