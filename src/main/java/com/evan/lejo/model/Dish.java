package com.evan.lejo.model;

import com.evan.lejo.configuration.response.Error;
import com.evan.lejo.exception.HttpUnprocessableEntityException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dish {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long   id;
    private String title;
    private String description;
    private Double price;


    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle( String title ) {
        if ( title == null || title.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_MOBILE_REQUIRED );
        }

        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription( String description ) {
        if ( description == null || description.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.ACCOUNT_INFORMATION_MOBILE_REQUIRED );
        }

        this.description = description;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice( Double price ) {
        if ( price < 0 ) {
            throw new HttpUnprocessableEntityException( Error.DISH_PRICE_NOT_ALLOWED );
        }

        this.price = price;
    }
}
