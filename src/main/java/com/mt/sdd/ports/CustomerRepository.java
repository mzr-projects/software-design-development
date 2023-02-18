package com.mt.sdd.ports;

import com.mt.sdd.domains.customer.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Modifying
    @Query("delete from Customer c where c.id=:id")
    Integer deleteCustomers(@Param("id") Integer id);

    @Query("from Customer c")
    List<Customer> findAllCustomers();
}
