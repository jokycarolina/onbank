package com.onbank.onbank.model;

import javax.persistence.*;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_currency")
    private int idCurrency;

    private String currency;

    public int getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(int idCurrency) {
        this.idCurrency = idCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "idCurrency=" + idCurrency +
                ", currency='" + currency + '\'' +
                '}';
    }
}
