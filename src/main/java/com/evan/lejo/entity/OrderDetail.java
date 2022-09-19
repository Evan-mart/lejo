package com.evan.lejo.entity;

import javax.persistence.*;

@Entity
public class OrderDetail {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "quantity" )
    private Long quantity;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn( name = "dish_id", nullable = false )
    private Dish dish;

    @ManyToOne( cascade = CascadeType.PERSIST )
    @JoinColumn( name = "order_id", nullable = false )
    private Orders order;


    public Long getId() {
        return id;
    }


    public void setId( Long id ) {
        this.id = id;
    }


    public Long getQuantity() {
        return quantity;
    }


    public void setQuantity( Long quantity ) {
        this.quantity = quantity;
    }


    public Dish getDish() {
        return dish;
    }


    public void setDish( Dish dish ) {
        this.dish = dish;
    }


    public Orders getOrder() {
        return order;
    }


    public void setOrder( Orders order ) {
        this.order = order;
    }


    @Override
    public String toString() {
        return this.id + " : " + this.quantity + " x " + this.dish;
    }
}
