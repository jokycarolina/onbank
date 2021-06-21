package com.onbank.onbank.repository;

import com.onbank.onbank.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface IPaymentRepository  extends CrudRepository<Payment,Integer> {
}
