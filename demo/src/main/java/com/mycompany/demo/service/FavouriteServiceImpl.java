package com.mycompany.demo.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.demo.config.DemoSettings;
import com.mycompany.demo.converter.FavouriteConverter;
import com.mycompany.demo.dto.FavouriteDto;
import com.mycompany.demo.entity.Favourite;
import com.mycompany.demo.entity.Movie;
import com.mycompany.demo.entity.Person;
import com.mycompany.demo.repository.FavouriteRepository;
import com.mycompany.demo.service.exception.BadRequestException;

@Service
public class FavouriteServiceImpl implements FavouriteService {

	@Resource
	PersonService personService;
	
	@Resource
	MovieService movieService;

	@Resource
	FavouriteRepository favouriteRepository;
	
	@Resource
	FavouriteConverter favouriteConverter;
	
	@Override
	public FavouriteDto getFavouritesByPersonId(Integer personId) {
		// 
		Person person =	personService.getPersonById(personId);	
		FavouriteDto favouriteDto = new FavouriteDto();
		//
		favouriteDto.setId(person.getId());
		favouriteDto.setFirstName(person.getFirstName());
		favouriteDto.setLastName(person.getLastName());
		favouriteDto.setHasInsurance(person.getHasInsurance());
		//
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate = dateFormat.format(person.getBirthdate()); 
		favouriteDto.setBirthdate(strDate);
		//
		List<Movie> movieList = new ArrayList<>();
		List<Favourite> favouriteList = favouriteRepository.findFavouritesByPersonId(personId);
		//
		for(Favourite favourite : favouriteList) {
			//
			Integer movieId = favourite.getMovie().getId();
			Movie movie = movieService.getMovieById(movieId);
			//
			movieList.add(movie);
		}
		favouriteDto.setFavouriteMovies(movieList);
		//
		return favouriteDto;
	}

	@Override
	public Favourite addMovieToFavourite(Integer movieId, Integer personId) throws IOException {
		// 
		Integer maxMovies = DemoSettings.MAX_MOVIES_PERSON;
		
		List<Favourite> favouriteList = favouriteRepository.findFavouritesByPersonId(personId);
		if((favouriteList.size() + 1) > maxMovies) {
			throw new BadRequestException("Se ha superado el numero maximo" );
		}
		
		Favourite favourite = new Favourite();
		Person person = personService.getPersonById(personId);
		favourite.setPerson(person);
		//
		Movie movie = movieService.getMovieById(movieId);
		favourite.setMovie(movie);
		//
		Favourite newFavourite = favouriteRepository.save(favourite);
		
		return newFavourite;
	}

	@Override
	public Integer deleteMovieFromFavourite(Integer movieId, Integer personId) {

		Favourite favouriteForDelete =  favouriteRepository.findFavouritesByPersonIdAndMovieId(personId, movieId);
		if(favouriteForDelete == null) {
			return 0;
		}
		else {
			favouriteRepository.delete(favouriteForDelete);
		}
		//
		favouriteForDelete = favouriteRepository.findFavouritesByPersonIdAndMovieId(personId, movieId);
		if(favouriteForDelete == null) {
			return 1;
		}
		return 0;

	}

}
