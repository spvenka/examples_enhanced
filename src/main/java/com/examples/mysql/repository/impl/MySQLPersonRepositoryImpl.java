package com.examples.mysql.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.examples.common.repository.PersonRepository;
import com.examples.common.domain.Person;
import com.examples.common.domain.PersonDTO;
import com.examples.mysql.domain.MySQLPerson;

/**
 * Repository for {@link Person}s
 */
@Repository
@Scope("singleton")
public class MySQLPersonRepositoryImpl extends CustomHibernateDaoSupport implements PersonRepository{

    static final Logger logger = LoggerFactory.getLogger(MySQLPersonRepositoryImpl.class);

    @SuppressWarnings("unchecked")
	@Override
    public List<MySQLPerson> findAllPersons() {
        return getHibernateTemplate().loadAll(MySQLPerson.class);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<MySQLPerson> findByName(String name) {
        return getHibernateTemplate().findByNamedParam("from MySQLPerson p where p.name like :name", "name", name);
    }

    @SuppressWarnings("unchecked")
	@Override
    public MySQLPerson findById(String id) {
        return (MySQLPerson) getHibernateTemplate().findByNamedParam("from com.examples.mysql.domain.MySQLPerson p where p.personId = :personId", "personId", Integer.parseInt(id)).get(0);
    }      
    
    @Override
    public void create(PersonDTO person) {
    	getHibernateTemplate().save(person);
    }
    
    @Override
    public void update(PersonDTO person)	{
    	getHibernateTemplate().saveOrUpdate(person);
	} 

    @Override
    public void delete(PersonDTO person) {
    	getHibernateTemplate().delete(person);
    }

    /**
     * Create a {@link Person} collection if the collection does not already exists
     */
    @Override
    public void createPersonCollection() {
        
    }

    /**
     * Drops the {@link Person} collection if the collection does already exists
     */
    @Override
    public void dropPersonCollection() {
        
    }
}
