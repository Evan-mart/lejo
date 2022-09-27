package com.evan.lejo.entity;

import com.evan.lejo.api.json.annotation.Group;
import com.evan.lejo.api.json.annotation.Json;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.configuration.response.Error;
import com.evan.lejo.exception.HttpUnprocessableEntityException;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table( name = "account_information" )
public class AccountInformation {

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    private String mobile;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    private String address;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    private String city;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    @Column( name = "post_code" )
    private String postCode;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    @OneToOne( cascade = CascadeType.PERSIST )
    @JoinColumn( name = "account_id" )
    private Account account;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    @Column( name = "created_at" )
    private final ZonedDateTime createdAt;


    public AccountInformation() {
        createdAt = ZonedDateTime.now( ZoneId.of( "UTC" ) );
    }


    public Long getId() {
        return id;
    }


    public String getMobile() {
        return mobile;
    }


    public AccountInformation setMobile( String mobile ) {
        if ( mobile == null || mobile.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_MOBILE_REQUIRED );
        }

        if ( mobile.length() > 10 ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_MOBILE_INVALID );
        }

        this.mobile = mobile;

        return this;
    }


    public String getAddress() {
        return address;
    }


    public AccountInformation setAddress( String address ) {
        if ( address == null || address.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_ADDRESS_REQUIRED );
        }

        this.address = address;

        return this;
    }


    public String getCity() {
        return city;
    }


    public AccountInformation setCity( String city ) {
        if ( city == null || city.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_CITY_REQUIRED );
        }

        this.city = city;

        return this;
    }


    public String getPostCode() {
        return postCode;
    }


    public AccountInformation setPostCode( String postCode ) {
        if ( postCode == null || postCode.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_POSTCODE_REQUIRED );
        }

        this.postCode = postCode;

        return this;
    }


    public Account getAccount() {
        return account;
    }


    public AccountInformation setAccount( Account account ) {
        this.account = account;

        return this;
    }


    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
