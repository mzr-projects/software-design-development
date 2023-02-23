package com.mt.sdd.domains.person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    private Set<Phone> phones = new HashSet<>();

    public void setPhones(Set<String> phoneNumbers) {
        for (String phoneTemp : phoneNumbers) {
            Phone phone = new Phone();
            phone.setPhoneNumber(phoneTemp);
            phones.add(phone);
        }
    }
}