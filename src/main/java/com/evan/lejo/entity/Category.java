package com.evan.lejo.entity;

import com.evan.lejo.api.json.annotation.Group;
import com.evan.lejo.api.json.annotation.Json;
import com.evan.lejo.configuration.json.GroupType;
import com.evan.lejo.configuration.response.Error;
import com.evan.lejo.exception.HttpUnprocessableEntityException;

import javax.persistence.*;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Entity
@Table( name = "category" )
public class Category {

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Json( groups = {
            @Group( name = GroupType.ADMIN ),
            @Group( name = GroupType.USER )
    } )
    private String name;


    public Long getId() {
        return id;
    }


    public Category setId( Long id ) {
        this.id = id;

        return this;
    }


    public String getName() {
        return name;
    }


    public Category setName( String name ) {
        if ( name == null || name.isBlank() ) {
            throw new HttpUnprocessableEntityException( Error.CATEGORY_NAME_REQUIRED );
        }
        this.name = name;

        return this;
    }
}
