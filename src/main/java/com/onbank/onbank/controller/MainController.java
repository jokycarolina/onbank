package com.onbank.onbank.controller;

import com.onbank.onbank.model.*;


import com.onbank.onbank.repository.*;
import com.onbank.onbank.service.ICurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/onbank")
public class MainController {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ICurrencyRepository currencyRepository;

    @Autowired
    private ITypeAccountRepository typeAccountRepository;

    @Autowired
    private IPhoneNumberRepository phoneNumberRepository;
  // ***************************************GET*******************************************************************
    //Traer todos los clientes
    @GetMapping(path = "/customers")
    @ResponseBody
    ResponseEntity<Customer> getAllCustomer(){
        Iterable<Customer> customerList=customerRepository.findAll();
        return new ResponseEntity(customerList, HttpStatus.OK) ;
    }

    //Traer todos las cuentas
    @GetMapping(path = "/accounts")
    @ResponseBody
    Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    //Traer todos los tipos de cuentas
    @GetMapping(path = "/typeaccounts")
    @ResponseBody
    Iterable<TypeAccount> getAllTypeAccounts() {
        return typeAccountRepository.findAll();
    }

    //Traer todos los tipos de moneda
    @GetMapping(path = "/currency")
    @ResponseBody
    Iterable<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }
    //Traer todos los telefonos
    @GetMapping(path = "/phonenumbers")
    @ResponseBody
    Iterable<PhoneNumber> getAllPhoneNumbers(){
        return phoneNumberRepository.findAll();
    }

    //Traer customer por id
    @GetMapping(path = "/customer/{id}")
    @ResponseBody
    ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
                return new ResponseEntity(customer,HttpStatus.FOUND);
            }
        return new ResponseEntity("ID NO VALIDO",HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/account/{id}")
    @ResponseBody
    ResponseEntity<Account> getAccountById(@PathVariable("id") int id){
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            return new ResponseEntity(account,HttpStatus.FOUND);
        }
        return new ResponseEntity("ID NO VALIDO",HttpStatus.BAD_REQUEST);
    }


    @GetMapping(path = "/typeaccount/{id}")
    @ResponseBody
    ResponseEntity<TypeAccount> getTypeAccountById(@PathVariable("id") int id){
        Optional<TypeAccount> typeAccount = typeAccountRepository.findById(id);
        if(typeAccount.isPresent()){
            return new ResponseEntity(typeAccount,HttpStatus.FOUND);
        }
        return new ResponseEntity("ID NO VALIDO",HttpStatus.BAD_REQUEST);
    }


    @GetMapping(path = "/currency/{id}")
    @ResponseBody
    ResponseEntity<Currency> getCurrencyById(@PathVariable("id") int id){
        Optional<Currency> currency = currencyRepository.findById(id);
        if(currency.isPresent()){
            return new ResponseEntity(currency,HttpStatus.FOUND);
        }
        return new ResponseEntity("ID NO VALIDO",HttpStatus.BAD_REQUEST);
    }

    ////////////// POST /////

    @PostMapping(path="/",consumes = "application/json", produces = "application/json")
    ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Account newAccount = new Account();// creamos nueva cuenta
        Optional<Currency> currency1 = currencyRepository.findById(1); // asignamos por default la moneda en pesos
        Optional<TypeAccount> typeAccount = typeAccountRepository.findById(1); //buscamos tipo de cuenta para que sea de ahorro
        //Asignamos a una cuenta nueva, un tipo de cuenta AHORRO y en moneda PESOS
        newAccount.setTypeAccount(typeAccount.get());
        newAccount.setCurrency(currency1.get());
        newAccount.setAmount(0.00);
        List<Account> accounts = new ArrayList<>();
        accounts.add(newAccount);
        customer.setAccounts(accounts);
        Customer newCustomer = customerRepository.save(customer);
       return new ResponseEntity<>(newCustomer,HttpStatus.CREATED);
    }
}
