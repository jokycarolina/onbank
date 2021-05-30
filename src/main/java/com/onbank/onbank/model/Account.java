package com.onbank.onbank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_account")
    private int idAccount;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name= "currency",referencedColumnName = "id_currency")
    private Currency currency;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name= "type_account",referencedColumnName = "id_type")
    private TypeAccount typeAccount;

    private Double amount;

    @JsonBackReference
    @ManyToMany(mappedBy = "accounts")
    private List<Customer> customer;



    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(TypeAccount typeAccount) {
        this.typeAccount = typeAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", currency=" + currency +
                ", typeAccount=" + typeAccount +
                ", amount=" + amount +
                '}';
    }
}
