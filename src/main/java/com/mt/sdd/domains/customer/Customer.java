package com.mt.sdd.domains.customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq")
    @Column(name = "CUSTOMER_ID")
    private Integer id;

    private String name;

    /*
     * Here customer is the owner of the relation-ship and cart is the counterpart
     * */
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Cart.class)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

}
