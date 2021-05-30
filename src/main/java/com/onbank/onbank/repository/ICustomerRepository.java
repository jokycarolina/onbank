package com.onbank.onbank.repository;

import com.onbank.onbank.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository  extends CrudRepository<Customer,Integer> {
}
