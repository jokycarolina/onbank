package com.onbank.onbank.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="extraction_order")
public class ExtractionOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_extraction")
    private int idExtraction;

    @OneToOne
    @JoinColumn(name = "id_account",referencedColumnName = "id_account")
    private Account idAccount;

    @Column(name="amount_extraction")
    private Double amountExtraction;

    @Column(name="date_extraction")
    private Date dateExtraction;

    public int getIdExtraction() {
        return idExtraction;
    }

    public void setIdExtraction(int idExtraction) {
        this.idExtraction = idExtraction;
    }

    public Account getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Account idAccount) {
        this.idAccount = idAccount;
    }

    public Double getAmountExtraction() {
        return amountExtraction;
    }

    public void setAmountExtraction(Double amountExtraction) {
        this.amountExtraction = amountExtraction;
    }

    public Date getDateExtraction() {
        return dateExtraction;
    }

    public void setDateExtraction(Date dateExtraction) {
        this.dateExtraction = dateExtraction;
    }


    @Override
    public String toString() {
        return "ExtractionOrder{" +
                "idExtraction=" + idExtraction +
                ", idAccount=" + idAccount +
                ", amountExtraction=" + amountExtraction +
                ", dateExtraction=" + dateExtraction +
                '}';
    }
}
