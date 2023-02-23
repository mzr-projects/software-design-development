package com.mt.sdd.controllers;

import com.mt.sdd.domains.person.Person;
import com.mt.sdd.domains.person.PersonDto;
import com.mt.sdd.ports.IDDService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/person/one-to-many-uni-directional")
@RequiredArgsConstructor
@Slf4j
public class PersonController {

    private final IDDService<PersonDto, Person> personService;

    @PostMapping("/create")
    public void create(@RequestBody PersonDto personDto) {
        personService.create(personDto);
        log.info("Person created.");
    }

    @GetMapping("/findAll")
    public Collection<PersonDto> findAll(){
        return personService.findAll();
    }
}
