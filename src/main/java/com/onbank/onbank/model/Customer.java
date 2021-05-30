package com.onbank.onbank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "customer_dni")
    private String costumerDni;

    private String fullname;
    @Column(name = "date_birth")
    private Date date_birth;
    private String email;
    private int password;
    @JsonManagedReference
    @JoinTable(
            name = "customer_account",
            joinColumns = @JoinColumn(name = "id_customer"),
            inverseJoinColumns = @JoinColumn(name = "id_account"))
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Account> accounts;

    @JsonBackReference
    @ManyToMany(mappedBy = "idCustomer")
     private List<Payment> payments;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCostumerDni() {
        return costumerDni;
    }

    public void setCostumerDni(String costumerDni) {
        this.costumerDni = costumerDni;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", costumerDni='" + costumerDni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", date_birth=" + date_birth +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", accounts=" + accounts +

                '}';
    }
}
