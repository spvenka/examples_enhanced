package com.examples.mongodb.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.examples.common.domain.PersonDTO;
import com.examples.common.repository.PersonRepository;
import com.examples.mongodb.domain.MongoDBPerson;

/**
 * Repository for {@link Person}s
 */
//@Repository
@Scope("singleton")
public class MongoDBPersonRepositoryImpl implements PersonRepository{

    static final Logger logger = LoggerFactory.getLogger(MongoDBPersonRepositoryImpl.class);

    @Autowired
    MongoTemplate mongoTemplate;
    
    @SuppressWarnings("unchecked")
	@Override
    public List<MongoDBPerson> findAllPersons() {
        return mongoTemplate.findAll(MongoDBPerson.class);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<MongoDBPerson> findByName(String name) {
        return mongoTemplate.find(new Query(Criteria.where("name").is(name)), MongoDBPerson.class);
    }

    @Override
    public MongoDBPerson findById(String id) {
        return mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), MongoDBPerson.class);
    }      
    
    @Override
    public void create(PersonDTO person) {
        mongoTemplate.insert(person);
    }
    
    @Override
    public void update(PersonDTO person)	{
		mongoTemplate.save(person);
	} 

    @Override
    public void delete(PersonDTO person) {
    	mongoTemplate.remove(person);
    }

    /**
     * Create a {@link Person} collection if the collection does not already exists
     */
    @Override
    public void createPersonCollection() {
        if (!mongoTemplate.collectionExists(PersonDTO.class)) {
            mongoTemplate.createCollection(PersonDTO.class);
        }
    }

    /**
     * Drops the {@link Person} collection if the collection does already exists
     */
    @Override
    public void dropPersonCollection() {
        if (mongoTemplate.collectionExists(PersonDTO.class)) {
            mongoTemplate.dropCollection(PersonDTO.class);
        }
    }
}
