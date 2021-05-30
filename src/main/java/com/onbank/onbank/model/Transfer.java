package com.onbank.onbank.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_transfer")
    private int idTransfer;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name= "id_customer",referencedColumnName = "customer_id")
    private Customer idCustomer;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name= "id_account",referencedColumnName = "id_account")
    private Account idAccount;

    private Double amount;

    @Column(name="date_transfer")
    private Date dateTransfer;

    public int getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(int idTransfer) {
        this.idTransfer = idTransfer;
    }

    public Customer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Customer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Account getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Account idAccount) {
        this.idAccount = idAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(Date dateTransfer) {
        this.dateTransfer = dateTransfer;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "idTransfer=" + idTransfer +
                ", idCustomer=" + idCustomer +
                ", idAccount=" + idAccount +
                ", amount=" + amount +
                ", dateTransfer=" + dateTransfer +
                '}';
    }
}
