package com.mycompany.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mycompany.demo.entity.Favourite;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {
	
	@Query("SELECT f FROM Favourite f where f.person.id = :personId")
	List<Favourite> findFavouritesByPersonId(@Param("personId") Integer personId);	

	@Query("SELECT f FROM Favourite f where f.person.id = :personId and f.movie.id = :movieId")
	Favourite findFavouritesByPersonIdAndMovieId(@Param("personId") Integer personId, @Param("movieId") Integer movieId);	
}
