package com.mycompany.demo.converter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mycompany.demo.dto.PersonDto;
import com.mycompany.demo.entity.Person;

@Service
public class PersonConverter extends GenericConverter<PersonDto, Person>  {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	
	@Override
	public PersonDto toDto(Person entity) throws IOException {
		
		PersonDto personDto = new PersonDto();
		//
		personDto.setId(entity.getId());
		personDto.setFirstName(entity.getFirstName());
		personDto.setLastName(entity.getLastName());
		String strDate = dateFormat.format(entity.getBirthdate()); 
		personDto.setBirthdate(strDate);
		personDto.setHasInsurance(entity.getHasInsurance());
		//
		return personDto;	
	}

	@Override
	public Person toEntity(PersonDto personDto) throws Exception {
		// 
		Person person = new Person();
		person.setId(personDto.getId());
		person.setFirstName(personDto.getFirstName());
		person.setLastName(personDto.getLastName());
		
	    String strDate = personDto.getBirthdate();  
	    Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);  
		person.setBirthdate(birthDate);
		
		person.setHasInsurance(personDto.getHasInsurance());
		return person;
	}
	
	@Override
	public  List<PersonDto> toDtoList(List<Person> personList) throws IOException {
		List<PersonDto> personDtoList = new ArrayList<>();
		for(Person person : personList) {
			personDtoList.add(toDto(person));	
		}
		return personDtoList;
	}
	

}
