package com.evan.lejo.model;

import com.evan.lejo.configuration.response.Error;
import com.evan.lejo.exception.HttpUnprocessableEntityException;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
public class AccountInformation {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private String email;
    private String mobile;
    private String address;
    private String city;

    @Column( name = "post_code" )
    private String postCode;

    @Column( name = "created_at" )
    private final ZonedDateTime createdAt;


    public AccountInformation() {
        createdAt = ZonedDateTime.now( ZoneId.of( "UTC" ) );
    }


    public Long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail( String email ) {
        this.email = email;
    }


    public String getMobile() {
        return mobile;
    }


    public void setMobile( String mobile ) {
        if ( mobile == null || mobile.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_MOBILE_REQUIRED );
        }

        if ( mobile.length() != 10 ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_MOBILE_INVALID );
        }

        this.mobile = mobile;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress( String address ) {
        if ( address == null || address.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_ADDRESS_REQUIRED );
        }

        this.address = address;
    }


    public String getCity() {
        return city;
    }


    public void setCity( String city ) {
        if ( city == null || city.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_CITY_REQUIRED );
        }

        this.city = city;
    }


    public String getPostCode() {
        return postCode;
    }


    public void setPostCode( String postCode ) {
        if ( postCode == null || postCode.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_POSTCODE_REQUIRED );
        }

        this.postCode = postCode;
    }


    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
