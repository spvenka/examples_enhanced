package com.examples.common.service;

import java.util.List;

import com.examples.common.domain.Person;

public interface PersonService {
	public List<Person> findAllPersons();
	public List<Person> findByName(String query);
	public Person findById(String id);
	public Person create(Person person);
	public void update(Person person);
	public void delete(Person person);
	public int getAvarageAgeOfPerson();
	public void createPersonCollection();
	public void dropPersonCollection();
}
