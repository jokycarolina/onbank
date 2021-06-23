package com.onbank.onbank.repository;

import com.onbank.onbank.model.Transfer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ITransferRepository extends CrudRepository<Transfer, Integer> {

}
