package com.mycompany.demo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mycompany.demo.entity.Movie;
import com.mycompany.demo.service.MovieService;

@RestController
@RequestMapping("/netflix")
public class MovieController {

	@Resource
	MovieService movieService;
	
	@RequestMapping(value = "/movie/all", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public List<Movie> getPersons(HttpServletRequest request) throws Exception {
		 
		return movieService.getAllMovies();
	}
	
	@RequestMapping(value = "/movie", method = RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public Movie createPerson(@RequestBody Movie movie, HttpServletRequest request) throws Exception {
		 
		return movieService.createMovie(movie);
	}
	
}
