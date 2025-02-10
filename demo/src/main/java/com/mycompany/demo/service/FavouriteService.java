package com.mycompany.demo.service;

import java.io.IOException;

import com.mycompany.demo.dto.FavouriteDto;
import com.mycompany.demo.entity.Favourite;

public interface FavouriteService {

	Favourite addMovieToFavourite(Integer movieId, Integer personId) throws IOException;
	
	Integer deleteMovieFromFavourite(Integer movieId, Integer personId);

	FavouriteDto getFavouritesByPersonId(Integer personId);
}
