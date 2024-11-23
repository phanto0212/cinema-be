package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.Movie;
import com.phanvanto.cinema.Repository.MovieRespository;
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRespository movieRespository;
	
	@Override
	public List<Movie> getlist() {
		return movieRespository.getlist();
	}

	@Override
	public void AddorUpdate(Movie movie) {
		movieRespository.AddorUpdate(movie);
		
	}

	@Override
	public int deleteMovie(Long id) {
		return movieRespository.deleteMovie(id);
	}

	@Override
	public Movie getMovieById(Long id) {
		return movieRespository.getMovieById(id);
	}

	@Override
	public List<Movie> getAllMovieShowNow() {
		
		return movieRespository.getAllMovieShowNow();
	}

	@Override
	public List<Movie> getAllMovieComingSoon() {
		return movieRespository.getAllMovieComingSoon();
	}

}
