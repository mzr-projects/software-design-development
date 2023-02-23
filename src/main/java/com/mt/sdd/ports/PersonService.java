package com.mt.sdd.ports;

import com.mt.sdd.domains.person.Person;

public interface PersonService {

    Person findPersonByName(String name);

    void deleteByObject(Person person);

}
