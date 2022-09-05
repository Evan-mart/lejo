package com.evan.lejo.entity;

import com.evan.lejo.configuration.response.Error;
import com.evan.lejo.exception.HttpUnprocessableEntityException;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
public class Account {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private String username;

    private String password;

    @Column( name = "created_at", nullable = false )
    private final ZonedDateTime createdAt;

    @Column( name = "last_connection", nullable = false )
    private final ZonedDateTime lastConnection;

    @Column( name = "account_information_id" )
    private Long accountInformationId;

/*    @OneToMany( cascade = CascadeType.PERSIST, mappedBy = "account" )
    private final List< Order > orders;*/


    public Account() {
        createdAt      = ZonedDateTime.now( ZoneId.of( "UTC" ) );
        lastConnection = ZonedDateTime.now( ZoneId.of( "UTC" ) );
        //orders         = new ArrayList<>();
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


    public Account setPassword( String password ) {
        if ( password == null || password.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_PASSWORD_REQUIRED );
        }

        this.password = password;

        return this;
    }


    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }


    public ZonedDateTime getLastConnection() {
        return lastConnection;
    }


    public Long getAccountInformationId() {
        return accountInformationId;
    }


    public void setAccountInformationId( Long accountInformationId ) {
        this.accountInformationId = accountInformationId;
    }


/*    public List< Order > getOrders() {
        return orders;
    }


    public Account addOrder( Order order ) {
        if ( orders.contains( order ) ) {
            return this;
        }

        orders.add( order );

        return this;
    }*/
}

























