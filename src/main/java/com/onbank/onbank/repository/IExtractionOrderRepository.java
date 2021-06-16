package com.onbank.onbank.repository;

import com.onbank.onbank.model.ExtractionOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IExtractionOrderRepository extends CrudRepository<ExtractionOrder,Integer> {
    @Query(value="select * from extraction_order eo, account ac " +
            "where ac.id_account = eo.id_account and ac.id_account = ?1",nativeQuery = true)
    List<ExtractionOrder> OrderList (int idAccount);


}
