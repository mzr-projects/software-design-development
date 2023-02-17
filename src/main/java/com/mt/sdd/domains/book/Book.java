package com.mt.sdd.domains.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BOOK")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    /*
     * In every instance of book a corresponding instance of shipping exists
     * The shipping instance is created first and then stored as a reference in
     * the book entity and when a target entity in one-to-one and one-to-many
     * relationship is removed its often desirable to cascade the remove operation
     * to the target entity such target entities are considered orphans we set orphanRemoval
     * to true, so we remove them automatically.
     * */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shipping_id")
    private Shipping shipping;

}
