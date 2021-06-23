package com.onbank.onbank.repository;

import com.onbank.onbank.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface IAccountRepository extends CrudRepository<Account, Integer> {
}
