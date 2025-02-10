package com.mycompany.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.demo.entity.Movie;

public class FavouriteDto {

	private Integer id;
	
	@JsonProperty("first-name")
	private String firstName;

	@JsonProperty("last-name")
	private String lastName;

	@JsonProperty("birthdate")
	private String birthdate;

	@JsonProperty("has-insurance")
	private Boolean hasInsurance;
	
	@JsonProperty("favourite-movies")
	private List<Movie> favouriteMovies;

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

	public List<Movie> getFavouriteMovies() {
		return favouriteMovies;
	}

	public void setFavouriteMovies(List<Movie> favouriteMovies) {
		this.favouriteMovies = favouriteMovies;
	}



}
