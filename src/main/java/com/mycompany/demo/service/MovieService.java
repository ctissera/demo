package com.mycompany.demo.service;

import java.util.List;

import com.mycompany.demo.entity.Movie;

public interface MovieService {

	List<Movie> getAllMovies();
	
	Movie getMovieById(Integer movieId);
	
	Movie createMovie(Movie movie) ;
}
