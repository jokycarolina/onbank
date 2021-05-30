package com.onbank.onbank.model;

import javax.persistence.*;

@Entity
@Table(name="consultation")
public class Cosultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_consultation")
    private int idConsultation;

    @OneToOne
    @JoinColumn(name = "id_customer",referencedColumnName = "customer_id")
    private Customer idCustomer;

    @OneToOne
    @JoinColumn(name = "id_account",referencedColumnName = "id_account")
    private Account idAccount;

   private Double amount;


    public Customer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCostumer(Customer idCostumer) {
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

    @Override
    public String toString() {
        return "Cosultation{" +
                "idCostumer=" + idCustomer +
                ", idAccount=" + idAccount +
                ", amount=" + amount +
                '}';
    }
}
