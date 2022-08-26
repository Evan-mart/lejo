package com.evan.lejo.model;

import com.evan.lejo.configuration.response.Error;
import com.evan.lejo.exception.HttpUnprocessableEntityException;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table( name = "`order`" )
public class Order {

    public static final byte STATUS_INITIALIZED                    = 0;
    public static final byte STATUS_IN_PROGRESS                    = 1;
    public static final byte STATUS_DELIVERY_INFORMATION_COMPLETED = 2;
    public static final byte STATUS_PAYED                          = 3;
    public static final byte STATUS_VALIDATED                      = 4;
    public static final byte STATUS_IN_DELIVERY                    = 5;
    public static final byte STATUS_DELIVERED                      = 6;
    public static final byte STATUS_ABORTED                        = 7;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    private byte status;

    @Column( name = "created_at" )
    private final ZonedDateTime createdAt;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn( name = "account_id", nullable = false )
    private Account account;


    public Order() {
        createdAt = ZonedDateTime.now( ZoneId.of( "UTC" ) );
    }


    public Long getId() {
        return id;
    }


    public byte getStatus() {
        return status;
    }


    public Order setStatus( Byte status ) {
        if ( status == null ) {
            throw new HttpUnprocessableEntityException( Error.ORDER_STATUS_REQUIRED );
        }

        if ( status != STATUS_INITIALIZED
                && status != STATUS_IN_PROGRESS
                && status != STATUS_DELIVERY_INFORMATION_COMPLETED
                && status != STATUS_PAYED
                && status != STATUS_VALIDATED
                && status != STATUS_IN_DELIVERY
                && status != STATUS_DELIVERED
                && status != STATUS_ABORTED ) {
            throw new HttpUnprocessableEntityException( Error.ORDER_STATUS_INVALID );
        }

        this.status = status;

        return this;
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
}




















