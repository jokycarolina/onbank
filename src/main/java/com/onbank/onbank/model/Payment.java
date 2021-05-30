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

    @JsonManagedReference
    @JoinTable(
            name = "payment_customer",
            joinColumns = @JoinColumn(name = "id_payment"),
            inverseJoinColumns = @JoinColumn(name = "id_customer"))
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Customer> idCustomer;

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

    public List<Customer> getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(List<Customer> idCustomer) {
        this.idCustomer = idCustomer;
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