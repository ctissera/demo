package com.mycompany.demo.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mycompany.demo.dto.PersonDto;
import com.mycompany.demo.entity.Person;

public interface PersonService {

	List<PersonDto> getAllPerson() throws IOException;

	Person getPersonById(Integer personId);
	
	Page<Person> getPersonByName(String search, Pageable pageable);
	
	PersonDto createPerson(PersonDto personDto) throws Exception;
	
	Integer deletePerson(Integer personId);

	PersonDto updatePerson(PersonDto newPerson) throws ParseException, IOException, Exception;

}
