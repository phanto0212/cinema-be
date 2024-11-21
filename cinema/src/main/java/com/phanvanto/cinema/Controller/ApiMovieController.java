package com.phanvanto.cinema.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
