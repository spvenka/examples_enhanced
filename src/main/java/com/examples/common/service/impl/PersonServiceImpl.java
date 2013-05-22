package com.examples.common.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.examples.common.domain.Person;
import com.examples.common.domain.PersonDTO;
import com.examples.common.repository.PersonRepository;
import com.examples.common.service.PersonService;

/**
 * Service for {@link Person}s
 */
@Service
@Scope("singleton")
public class PersonServiceImpl implements PersonService{

    static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired private PersonRepository personRepository;
    @Autowired Mapper dozerBeanMapper;
    
    @Override
    public List<Person> findAllPersons() {
    	//List<Person> results = personRepository.findAllPersons();
    	List<Person> list = new ArrayList<Person>();
        for (Object person : personRepository.findAllPersons()) {
            list.add(dozerBeanMapper.map(person, Person.class));
        }
        logger.info("Total amount of persons: {}", list.size());
        logger.info("Results: {}", list);
        
        return list;
    }
    
    @Override
    public List<Person> findByName(String name) {
    	//List<Person> results = personRepository.findByName(name);
    	List<Person> list = new ArrayList<Person>();
        for (Object person : personRepository.findByName(name)) {
            list.add(dozerBeanMapper.map(person, Person.class));
        }
        return list;
    }
    
    @Override
    public Person findById(String id) {
    	Object person1 = dozerBeanMapper.map(personRepository.findById(id), Person.class);
    	return (Person) person1;
    }  
    
    @Override
    public Person create(Person person) {    	
    	PersonDTO personDTO = dozerBeanMapper.map(person, PersonDTO.class);
        personRepository.create(personDTO);
        return dozerBeanMapper.map(personDTO, Person.class);
    }

    @Override
    public void update(Person person) {    	
    	PersonDTO person1 = dozerBeanMapper.map(person, PersonDTO.class);
        personRepository.update(person1);
    }

    @Override
    public void delete(Person person) {    	
    	PersonDTO person1 = dozerBeanMapper.map(person, PersonDTO.class);
    	personRepository.delete(person1);
    }

    @Override
    public int getAvarageAgeOfPerson() {
    	List<Person> results = personRepository.findAllPersons();
        int age = 0;
        Iterator<Person> personIterator = results.iterator();
        while (personIterator.hasNext()) {
            Person nextPerson = personIterator.next();
            age += nextPerson.getAge();
        }
        return age / results.size();
    } 

    @Override
    public void dropPersonCollection() {
        personRepository.dropPersonCollection();
    }

    @Override
    public void createPersonCollection() {
        personRepository.createPersonCollection();
    }
}
