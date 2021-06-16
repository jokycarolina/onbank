package com.onbank.onbank.model;

import javax.persistence.*;

@Entity
public class Deposit {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;

 private double amount;

@Column(name="id_cliente")
private String dniClient;

@OneToOne(cascade = CascadeType.DETACH)
@JoinColumn(name= "id_account",referencedColumnName = "id_account")
private Account account;

@OneToOne(cascade = CascadeType.DETACH)
@JoinColumn(name= "id_currency",referencedColumnName = "id_currency")
private Currency currency;

 public Deposit(int id, double amount, String dniClient, Account account, Currency currency) {
  this.id = id;
  this.amount = amount;
  this.dniClient = dniClient;
  this.account = account;
  this.currency = currency;
 }

 public Deposit() {
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public double getAmount() {
  return amount;
 }

 public void setAmount(double amount) {
  this.amount = amount;
 }

 public String getDniClient() {
  return dniClient;
 }

 public void setDniClient(String dniClient) {
  this.dniClient = dniClient;
 }

 public Account getAccount() {
  return account;
 }

 public void setAccount(Account account) {
  this.account = account;
 }

 public Currency getCurrency() {
  return currency;
 }

 public void setCurrency(Currency currency) {
  this.currency = currency;
 }

 @Override
 public String toString() {
  return "Deposit{" +
          "id=" + id +
          ", amount=" + amount +
          ", dniClient=" + dniClient +
          ", account=" + account +
          ", currency=" + currency +
          '}';
 }
}
