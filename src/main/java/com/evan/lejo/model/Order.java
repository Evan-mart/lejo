package com.evan.lejo.model;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table( name = "`order`" )
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Column( name = "created_at" )
    private ZonedDateTime createdAt;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn( name = "account_id", nullable = false )
    private Account account;


    public Order() {
        this.createdAt = ZonedDateTime.now( ZoneId.of( "UTC" ) );
    }


    public Long getId() {
        return id;
    }


    public Account getAccount() {
        return account;
    }


    public Order setAccount( Account account ) {
        this.account = account;

        return this;
    }


    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt( ZonedDateTime createdAt ) {
        this.createdAt = createdAt;
    }
}




















