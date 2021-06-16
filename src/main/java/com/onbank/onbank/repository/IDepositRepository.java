package com.onbank.onbank.repository;

import com.onbank.onbank.model.Deposit;
import org.springframework.data.repository.CrudRepository;

public interface IDepositRepository extends CrudRepository<Deposit,Integer> {
}
