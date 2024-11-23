package com.phanvanto.cinema.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phanvanto.cinema.Entity.Movie;
import com.phanvanto.cinema.Service.MovieService;

@RestController
@RequestMapping("/api/movie")
public class ApiMovieController {
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllMovie(){
		List<Movie> movies = movieService.getlist();
		Map<String, Object> reponse = new HashMap();
		reponse.put("movies", movies);
		return ResponseEntity.ok(reponse);
		
	}
	@GetMapping("/all/show/now")
	public ResponseEntity<?> getAllMovieShowNow(){
		List<Movie> movies = movieService.getAllMovieShowNow();
		Map<String, Object> reponse = new HashMap();
		reponse.put("movies", movies);
		return ResponseEntity.ok(reponse);
		
	}
	@GetMapping("/get/movie/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") Long id){
		try {
			Movie movie = movieService.getMovieById(id);
			if(movie == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong ton tai movie voi ma "+ id);
			}
			Map<String, Object> reponse = new HashMap();
			reponse.put("movie", movie);
			return ResponseEntity.ok(reponse);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	

}
