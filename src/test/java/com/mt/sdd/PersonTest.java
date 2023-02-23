package com.mt.sdd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mt.sdd.domains.person.Person;
import com.mt.sdd.domains.person.PersonDto;
import com.mt.sdd.domains.person.Phone;
import com.mt.sdd.ports.IDDService;
import com.mt.sdd.ports.PersonService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@Transactional
@WebAppConfiguration
public class PersonTest {

    private final Gson gson = new GsonBuilder().create();

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PersonService personService;

    @Autowired
    private IDDService<PersonDto, Person> generalService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private static PersonDto createPersons() {
        Set<String> phones = new HashSet<>();
        phones.add("13133");
        phones.add("47847");
        phones.add("8732467");

        PersonDto personDto = new PersonDto();
        personDto.setName("John Wick");
        personDto.setNumbers(phones);

        return personDto;
    }

    @Test
    void testStudentCreation() {
        PersonDto personDto = createPersons();
        Person person = generalService.create(personDto);
        Person storedPerson = personService.findPersonByName(personDto.getName());
        Set<Phone> phones = storedPerson.getPhones();

        Assertions.assertEquals(person.getName(), personDto.getName());
        Assertions.assertEquals(storedPerson.getName(), personDto.getName());
        Assertions.assertEquals(phones, person.getPhones());
    }

    @Test
    void testStudentDeletion() {
        PersonDto personDto = createPersons();
        Person person = generalService.create(personDto);

        personService.deleteByObject(person);

        Person storedPerson = personService.findPersonByName(personDto.getName());

        Assertions.assertNull(storedPerson);
    }
}
