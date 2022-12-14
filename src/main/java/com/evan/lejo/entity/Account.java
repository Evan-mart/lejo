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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "account" )
public class Account {

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    protected long id;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    @Column( name = "username", nullable = false )
    protected String username;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    @Column( nullable = false )
    private String email;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    @JsonIgnore
    @Column( nullable = false )
    private String password;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    @Column( name = "created_at", nullable = false )
    private final ZonedDateTime createdAt;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    @Column( name = "last_connection", nullable = false )
    private final ZonedDateTime lastConnection;

    @ManyToMany( fetch = FetchType.LAZY )
    @JoinTable( name = "account_roles",
                joinColumns = @JoinColumn( name = "account_id" ),
                inverseJoinColumns = @JoinColumn( name = "role_id" ) )
    private List< Role > roles;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    @OneToMany( mappedBy = "account" )
    private List< Order > orders;


    public Account() {
        createdAt      = ZonedDateTime.now( ZoneId.of( "UTC" ) );
        lastConnection = ZonedDateTime.now( ZoneId.of( "UTC" ) );
        roles          = new ArrayList<>();
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


    public String getEmail() {
        return email;
    }


    public Account setEmail( String email ) {
        if ( email == null || email.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_EMAIL_REQUIRED );
        }

        this.email = email;

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


    public List< Role > getRoles() {
        return roles;
    }


    public void setRoles( List< Role > roles ) {
        this.roles = roles;
    }


    public List< Order > getOrders() {
        return orders;
    }


    public Account setOrders( List< Order > orders ) {
        this.orders = orders;

        return this;
    }
}

























