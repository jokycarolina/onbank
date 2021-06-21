package com.onbank.onbank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_payment")
    private int idPayment;

    @Column(name="date_payment")
    private Date datePayment;

    @OneToOne
    @JoinColumn(name = "currency",referencedColumnName = "id_currency")
    private Currency currency;

    private Double amount;


    @JoinTable(
            name = "payment_customer",
            joinColumns = @JoinColumn(name = "id_payment"),
            inverseJoinColumns = @JoinColumn(name = "id_customer"))

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<Customer> customerList;

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "idPayment=" + idPayment +
                ", datePayment=" + datePayment +
                ", currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}
