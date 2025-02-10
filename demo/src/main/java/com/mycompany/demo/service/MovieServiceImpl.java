package com.mycompany.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.demo.entity.Movie;
import com.mycompany.demo.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Resource
    MovieRepository movieRepository;

	@Override
	public List<Movie> getAllMovies() {
		//
		return movieRepository.findAllMovies();
	}
	
	@Override
	public Movie getMovieById(Integer movieId) {
		//
		return movieRepository.findMovieById(movieId);
	}

	@Override
	public Movie createMovie(Movie movie) {
		//
		return movieRepository.save(movie);
	}

}
