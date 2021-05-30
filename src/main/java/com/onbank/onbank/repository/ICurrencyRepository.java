package com.onbank.onbank.repository;

import com.onbank.onbank.model.Currency;
import org.springframework.data.repository.CrudRepository;

public interface ICurrencyRepository extends CrudRepository<Currency,Integer> {
}
