package com.mt.sdd.ports;

import com.mt.sdd.domains.customer.Cart;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Integer> {

    @Query("from Cart c")
    List<Cart> findAllCarts();

    @Modifying
    @Query("delete from Cart where id=:id")
    Integer deleteCart(@Param("id") Integer id);
}
