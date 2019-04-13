package com.main.controller;

import com.cassandra.entity.Person;
import com.cassandra.entity.PersonKey;
import com.cassandra.repository.PersonRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
public class SecondController {

//    private SecondRepository secondRepository;
    private PersonRepository personRepository;


    public SecondController(PersonRepository secondRepository) {
//        this.secondRepository = secondRepository;
        this.personRepository = secondRepository;
    }
//
//    @RequestMapping(method = RequestMethod.GET, path = "/get-by-id-second")
//    public String getById() {
//        String second = secondRepository.getByIdAndName(1L, "second1");
//        SecondEntity secondEntity = SecondEntity.builder().id(1L).isAvailable(true).name("second1").build();
//        System.out.println(secondEntity.hashCode());
//
//        return second;
//    }

    @RequestMapping(method = RequestMethod.GET, path = "/test-cassandra")
    public String casandra() {
        PersonKey personKey = new PersonKey("cassandra1", LocalDateTime.now(), UUID.randomUUID());
        Person person = new Person(personKey, "cassandra_last_name", 3000.0);

        Person insert = personRepository.insert(person);
        System.out.println(insert);
        List<Person> allPersons = personRepository.findAll();
        System.out.println(allPersons);

        return allPersons.toString();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/second")
    String home() {
        return "Hello World!\nI'm Second Micro-service";
    }
}
