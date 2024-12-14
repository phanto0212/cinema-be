package com.phanvanto.cinema.Service;

import java.util.List;

import com.phanvanto.cinema.Entity.Movie;

public interface MovieService {

	List<Movie> getlist();
	void AddorUpdate(Movie movie);
	int deleteMovie(Long id);
	Movie getMovieById(Long id);
	List<Movie> getAllMovieShowNow();
	List<Movie> getAllMovieComingSoon();
	List<Movie> getMovieByKey(String key);
}
