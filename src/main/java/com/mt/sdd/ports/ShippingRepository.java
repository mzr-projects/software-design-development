package com.mt.sdd.ports;

import com.mt.sdd.domains.book.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Integer> {
}
