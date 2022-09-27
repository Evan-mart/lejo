package com.evan.lejo.entity;

import com.evan.lejo.api.json.annotation.Group;
import com.evan.lejo.api.json.annotation.Json;
import com.evan.lejo.configuration.json.GroupType;

import javax.persistence.*;

@Entity
@Table( name = "role" )
public class Role {

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
    private String name;


    public Role() {
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public Role setName( String name ) {
        this.name = name;

        return this;
    }
}
