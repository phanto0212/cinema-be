package com.phanvanto.cinema.Repository;

import java.util.List;

import com.phanvanto.cinema.Entity.Movie;

public interface MovieRespository {

	List<Movie> getlist();
	void AddorUpdate(Movie movie);
	int deleteMovie(Long id);
	Movie getMovieById(Long id);
	List<Movie> getAllMovieShowNow();
	List<Movie> getAllMovieComingSoon();
	
}
