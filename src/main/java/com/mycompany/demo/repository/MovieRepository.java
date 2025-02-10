package com.mycompany.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mycompany.demo.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	@Query("SELECT m FROM Movie m")
	List<Movie> findAllMovies();
	
	@Query("SELECT m FROM Movie m where m.id = :movieId")
	Movie findMovieById(@Param("movieId") Integer movieId);
	
}
