package com.mycompany.demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.demo.dto.FavouriteDto;
import com.mycompany.demo.entity.Favourite;
import com.mycompany.demo.service.FavouriteService;

/* 
Películas de Personas
- Mostrar las películas de una persona
- Agregar una película a una persona
- Quitar una película de una persona
*/

@RestController
@RequestMapping("/netflix")
public class FavouriteController {

	@Resource
	FavouriteService favouriteService;
	
	@RequestMapping(value = "/favourite/person/{personId}", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public FavouriteDto getMoviesByPersonId(@PathVariable Integer personId, HttpServletRequest request) throws Exception {
		 
		return favouriteService.getFavouritesByPersonId(personId);
	}
	
	@RequestMapping(value = "/favourite/movie/{movieId}/person/{personId}", method = RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public Favourite addMovieToFavourite(@PathVariable Integer movieId, @PathVariable Integer personId, HttpServletRequest request) throws Exception {
		 
		return favouriteService.addMovieToFavourite(movieId ,personId);
	}

	@RequestMapping(value = "/favourite/movie/{movieId}/person/{personId}", method = RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public ResponseEntity<Integer> deleteMovieFromFavourite(@PathVariable Integer movieId, @PathVariable Integer personId, HttpServletRequest request) throws Exception {
		 
		Integer returnedValue = favouriteService.deleteMovieFromFavourite(movieId, personId);
		
		if(returnedValue == 0) {
			return new ResponseEntity<Integer>(0, HttpStatus.NOT_FOUND);			
		}
		else {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
	}
}
