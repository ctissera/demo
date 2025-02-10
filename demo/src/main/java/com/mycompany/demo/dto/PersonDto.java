package com.mycompany.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDto {
	
	private Integer id;
	
	@JsonProperty("first-name")
	private String firstName;

	@JsonProperty("last-name")
	private String lastName;

	@JsonProperty("birthdate")
	private String birthdate;

	@JsonProperty("has-insurance")
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

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Boolean getHasInsurance() {
		return hasInsurance;
	}

	public void setHasInsurance(Boolean hasInsurance) {
		this.hasInsurance = hasInsurance;
	}
	
	
}
