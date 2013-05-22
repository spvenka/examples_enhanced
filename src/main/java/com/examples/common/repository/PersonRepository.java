package com.examples.common.repository;

import java.util.List;

import com.examples.common.domain.PersonDTO;

public interface PersonRepository {
	public <T> List<T> findAllPersons();
	public <T> List<T> findByName(String name);
	public <T> T findById(String id);
	public void create(PersonDTO person);
	public void update(PersonDTO person);
	public void delete(PersonDTO person);
	public void createPersonCollection();
	public void dropPersonCollection();
}
