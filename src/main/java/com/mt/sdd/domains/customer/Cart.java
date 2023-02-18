package com.mt.sdd.domains.customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq")
    @Column(name = "CART_ID")
    private Integer id;

    private Double amount;

    /*
     * Here we reference to the cart field in the customer entity using mappedBy keyword
     * */
    @OneToOne(mappedBy = "cart", targetEntity = Customer.class)
    private Customer customer;

}
