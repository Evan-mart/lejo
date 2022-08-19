package com.evan.lejo.model;

import javax.persistence.*;

@Entity
public class AccountInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String mobile;
    private String address;
    private String city;

    @Column(name = "post_code")
    private Long postCode;


    public AccountInformation() {
    }

    public AccountInformation(
            String email,
            String mobile,
            String address,
            String city,
            Long postCode
    ) {
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPostCode() {
        return postCode;
    }

    public void setPostCode(Long postCode) {
        this.postCode = postCode;
    }
}
