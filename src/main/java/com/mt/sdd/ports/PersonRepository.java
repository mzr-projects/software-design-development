package com.mt.sdd.ports;

import com.mt.sdd.domains.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Integer> {

    Person findPersonByName(String name);
}
