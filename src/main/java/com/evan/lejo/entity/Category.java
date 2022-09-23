package com.evan.lejo.entity;

import com.evan.lejo.api.json.annotation.Group;
import com.evan.lejo.api.json.annotation.Json;
import com.evan.lejo.configuration.json.GroupType;

import javax.persistence.*;

/**
 * @author Evan Martinez <martinez.evan@orange.fr>
 */
@Entity
@Table( name = "category" )
public class Category {

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
    } )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Json( groups = {
            @Group( name = GroupType.ADMIN )
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
        this.name = name;

        return this;
    }
}
