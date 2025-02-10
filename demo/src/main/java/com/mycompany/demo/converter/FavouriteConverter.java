package com.mycompany.demo.converter;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.mycompany.demo.dto.FavouriteDto;
import com.mycompany.demo.entity.Favourite;

@Service
public class FavouriteConverter extends GenericConverter<FavouriteDto, Favourite> {

	@Override
	public FavouriteDto toDto(Favourite entity) throws IOException {
		FavouriteDto favouriteDto = new FavouriteDto();
		//
		favouriteDto.setId(entity.getId());
		favouriteDto.setFirstName(entity.getPerson().getFirstName());
		favouriteDto.setLastName(entity.getPerson().getLastName());
		favouriteDto.setBirthdate(entity.getPerson().getLastName());
		favouriteDto.setHasInsurance(entity.getPerson().getHasInsurance());
		//favouriteDto.setFavouriteMovies(entity.getMovie());
		//
		return favouriteDto;
	}

	@Override
	public Favourite toEntity(FavouriteDto dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
