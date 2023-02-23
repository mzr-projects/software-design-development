package com.mt.sdd.adapters;

import com.mt.sdd.domains.person.Person;
import com.mt.sdd.domains.person.PersonDto;
import com.mt.sdd.ports.IDDService;
import com.mt.sdd.ports.PersonRepository;
import com.mt.sdd.ports.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements IDDService<PersonDto, Person> , PersonService {

    private final PersonRepository personRepository;

    @Override
    public Person create(PersonDto personDto) {

        Person person = new Person();
        person.setName(personDto.getName());
        person.setPhones(personDto.getNumbers());

        return personRepository.save(person);
    }

    public Person findPersonByName(String name) {
        return personRepository.findPersonByName(name);
    }

    @Override
    public void delete(PersonDto personDto) {

    }

    public void deleteByObject(Person person) {
        personRepository.delete(person);
    }

    @Override
    public void edit(PersonDto personDto) {

    }

    @Override
    public List<PersonDto> findAll() {
        return null;
    }
}
