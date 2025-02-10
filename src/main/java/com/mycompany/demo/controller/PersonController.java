package com.mycompany.demo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.demo.dto.PersonDto;
import com.mycompany.demo.entity.Person;
import com.mycompany.demo.service.PersonService;

@RestController
@RequestMapping("/netflix")
public class PersonController {

	@Resource
	PersonService personService;
	
	@RequestMapping(value = "/person/all", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public List<PersonDto> getPersons(HttpServletRequest request) throws Exception {
		 
		return personService.getAllPerson();
	}	
	
	@RequestMapping(value = "/person/id/{id}", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public Person getPersonById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
		 
		return personService.getPersonById(id);
	}
	
	@RequestMapping(value = "/person/name/{search}", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public Page<Person> getPersonByName(@PathVariable String search, Pageable pageable, HttpServletRequest request) throws Exception {
		 
		return personService.getPersonByName(search, pageable);
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public PersonDto createPerson(@RequestBody PersonDto person, HttpServletRequest request) throws Exception {
		 
		return personService.createPerson(person);
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public PersonDto updatePerson(@RequestBody PersonDto personDto, HttpServletRequest request) throws Exception {
		 
		return personService.updatePerson(personDto);
	}
	
	@RequestMapping(value = "/person/{personId}", method = RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public ResponseEntity<Integer> deletePerson(@PathVariable Integer personId, HttpServletRequest request) throws Exception {
		
		Integer returnedValue = personService.deletePerson(personId);
		
		if(returnedValue == 0) {
			return new ResponseEntity<Integer>(0, HttpStatus.NOT_FOUND);			
		}
		else {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		
	}
}
