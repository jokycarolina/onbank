package com.onbank.onbank.model;

import javax.persistence.*;

@Entity
@Table(name="phone_number")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name= "customer_id",referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name="phone_number")
    private String phoneNumber;

    public Customer getCostumer() {
        return customer;
    }

    public void setCostumer(Customer costumer) {
        this.customer = costumer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "PhoneNumber{" +
                "customer=" + customer +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
