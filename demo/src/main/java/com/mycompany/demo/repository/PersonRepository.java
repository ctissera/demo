package com.mycompany.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mycompany.demo.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {


	@Query("SELECT p FROM Person p ORDER BY p.lastName, p.firstName")
	List<Person> findAllPerson();
	
	@Query("SELECT p FROM Person p where p.id = :personId")
	Person findPersonById(@Param("personId") Integer personId);
	
	@Query("SELECT p FROM Person p "
			+ "WHERE p.firstName LIKE CONCAT ('%', :search , '%') "
			+ "OR p.lastName LIKE CONCAT ('%', :search , '%') ")
	Page<Person> findPersonByName(@Param("search") String search, Pageable pageable);
	

}
