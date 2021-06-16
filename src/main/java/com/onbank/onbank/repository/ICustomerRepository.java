package com.onbank.onbank.repository;

import com.onbank.onbank.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository  extends CrudRepository<Customer,Integer> {
    @Query(
     value="SELECT * FROM customer as c where c.fullname LIKE %:param%",nativeQuery = true)
    List<Customer> getAllCustomerByLike(@Param("param") String param);
}
