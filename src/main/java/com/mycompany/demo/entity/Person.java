package com.mycompany.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Table(name="PERSONAS")
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SQ")
    @SequenceGenerator(sequenceName = "PERSON_SQ", allocationSize = 1, name = "PERSON_SQ")
	private Integer id;
	
	@Column(name="first_name", columnDefinition = "varchar2(100)")
	private String firstName;
	
	@Column(name="last_name", columnDefinition = "varchar2(100)")
	private String lastName;
	
	@Column(name="birthdate", columnDefinition = "date")
	private Date birthdate;
	
	@Column(name="has_insurance", columnDefinition = "varchar2(100)")
	private Boolean hasInsurance;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Boolean getHasInsurance() {
		return hasInsurance;
	}

	public void setHasInsurance(Boolean hasInsurance) {
		this.hasInsurance = hasInsurance;
	}
	
}
