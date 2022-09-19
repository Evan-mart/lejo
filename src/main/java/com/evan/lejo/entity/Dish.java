package com.evan.lejo.entity;

import com.evan.lejo.api.json.annotation.Group;
import com.evan.lejo.api.json.annotation.Json;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.configuration.response.Error;
import com.evan.lejo.exception.HttpUnprocessableEntityException;

import javax.persistence.*;

@Entity
@Table( name = "dish" )
public class Dish {

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    private String title;

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    private String description;

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    private Double price;


    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public Dish setTitle( String title ) {
        if ( title == null || title.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_MOBILE_REQUIRED );
        }

        this.title = title;

        return this;
    }


    public String getDescription() {
        return description;
    }


    public Dish setDescription( String description ) {
        if ( description == null || description.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_MOBILE_REQUIRED );
        }

        this.description = description;

        return this;
    }


    public double getPrice() {
        return price;
    }


    public Dish setPrice( Double price ) {
        if ( price < 0 ) {
            throw new HttpUnprocessableEntityException( Error.DISH_PRICE_NOT_ALLOWED );
        }

        this.price = price;

        return this;
    }
}
