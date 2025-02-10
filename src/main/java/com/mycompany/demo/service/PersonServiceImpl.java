package com.mycompany.demo.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mycompany.demo.converter.PersonConverter;
import com.mycompany.demo.dto.PersonDto;
import com.mycompany.demo.entity.Favourite;
import com.mycompany.demo.entity.Person;
import com.mycompany.demo.repository.PersonRepository;
import com.mycompany.demo.service.exception.BadRequestException;

@Service
public class PersonServiceImpl  implements PersonService  {

	@Resource
	PersonRepository personRepository;
	
	@Resource
	PersonConverter personConverter;
	
	@Override
	public List<PersonDto> getAllPerson() throws IOException {
		// 
		List<Person> personList = personRepository.findAllPerson();
		List<PersonDto> personDtoList = personConverter.toDtoList(personList);
		return personDtoList;
	}

	@Override
	public Person getPersonById(Integer personId) {
		// 
		return personRepository.findPersonById(personId) ;
	}

	@Override
	public Page<Person> getPersonByName(String search, Pageable pageable) {
		//
		return  personRepository.findPersonByName(search, pageable) ;
	}

	@Override
	public PersonDto createPerson(PersonDto personDto) throws Exception {
		// 
		if(personDto.getId() != null ) {
			Person oldPerson = personRepository.findPersonById(personDto.getId());
			if(oldPerson != null) {	
					throw new BadRequestException("Ya existe una persona con el DNI especificado " );			
			}
		}
		//
		Person person = personConverter.toEntity(personDto);
		return personConverter.toDto(personRepository.save(person));
	}

	@Override
	public PersonDto updatePerson(PersonDto newPersonDto) throws Exception {
		//
		Person newPerson = personConverter.toEntity(newPersonDto);
		
		Person oldPerson = personRepository.findPersonById(newPerson.getId());
		if(oldPerson == null) {	
				throw new BadRequestException("No existe la especificada " );			
		}
		//
		if(!oldPerson.getFirstName().equals(newPerson.getFirstName()) ) {
			oldPerson.setFirstName(newPerson.getFirstName());
		}
		//
		if(!oldPerson.getLastName().equals(newPerson.getLastName() )) {
			oldPerson.setLastName(newPerson.getLastName());
		}
		//
		if(!oldPerson.getBirthdate().equals( newPerson.getBirthdate() )) {
			oldPerson.setBirthdate(newPerson.getBirthdate());
		}
		//
		if(!oldPerson.getHasInsurance().equals( newPerson.getHasInsurance() ) ) {
			oldPerson.setHasInsurance(newPerson.getHasInsurance());
		}
		//
		Person per = personRepository.save(oldPerson);
		
		return personConverter.toDto(per);
	}

	@Override
	public Integer deletePerson(Integer personId) {
		//
		
		Person personForDelete =  personRepository.findPersonById(personId);
		if(personForDelete == null) {
			return 0;
		}
		else {
			personRepository.delete(personForDelete);
		}
		//
		personForDelete = personRepository.findPersonById(personId);
		if(personForDelete == null) {
			return 1;
		}
		return 0;
		
	}

	
}
