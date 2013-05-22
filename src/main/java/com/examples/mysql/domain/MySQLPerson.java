package com.examples.mysql.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.examples.common.domain.PersonDTO;

/**
 * A simple POJO representing a Person
 */
@Entity
@Table(name = "person", catalog = "persons", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name")})
/*@NamedQueries({

    @NamedQuery(name="Person.findAll", query="from Person per"),

    @NamedQuery(name="Person.findById", query="from Person per where per.personId = :id"),

    @NamedQuery(name="Person.findByName", query="from Person per where per.name = :name")
})*/
public class MySQLPerson extends PersonDTO implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int personId;

    private String name;
    private String homeTown;
    private int age;
    
    public MySQLPerson() {
		super();
	}
    
    public MySQLPerson(String name, int age) {
    	super();
        this.name = name;
        this.age = age;
    }
    
    public MySQLPerson(String name, int age, String homeTown) {
    	super();
        this.name = name;
        this.age = age;
        this.homeTown = homeTown;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(final int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(final String homeTown) {
        this.homeTown = homeTown;
    }

    @Override
    public String toString() {
        return "Person [id=" + personId + ", name=" + name
                + ", age=" + age + ", home town=" + homeTown + "]";
    }

}