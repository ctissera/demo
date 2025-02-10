package com.mycompany.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="FAVOURITES")
@Entity
@NamedQuery(name="Favourite.findAll", query="SELECT f FROM Favourite f")
public class Favourite implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FAVOURITE_SQ")
    @SequenceGenerator(sequenceName = "FAVOURITE_SQ", allocationSize = 1, name = "FAVOURITE_SQ")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "person_id", nullable=false)
	private Person person;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "movie_id", nullable=false)
	private Movie movie;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
}
