package com.evan.lejo.model;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "last_connection", nullable = false)
    private ZonedDateTime lastConnection;

    @Column(name = "account_information_id", nullable = false)
    private Long accountInformationId;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "account")
    private final List<Order> orders;


    public Account() {
        createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
        lastConnection = ZonedDateTime.now(ZoneId.of("UTC"));
        orders = new ArrayList<>();
    }


    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(ZonedDateTime lastConnection) {
        this.lastConnection = lastConnection;
    }

    public Long getAccountInformationId() {
        return accountInformationId;
    }

    public void setAccountInformationId(Long accountInformationId) {
        this.accountInformationId = accountInformationId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Account addOrder(Order order) {
        if (orders.contains( order )) {
            return this;
        }

        orders.add( order );

        return this;
    }
}

























