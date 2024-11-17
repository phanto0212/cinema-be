package com.phanvanto.cinema.Controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.phanvanto.cinema.Entity.Movie;
import com.phanvanto.cinema.Service.MovieService;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movies")
	public String Movies(Model model) {
		List<Movie> movies = movieService.getlist();
		model.addAttribute("movies", movies);
		return("Movies");
	}
	
	@PostMapping("/addOrUpdateMovie")
	public String addMovie(@ModelAttribute Movie movie) {
		try {
			movie.setCreated_at(new Timestamp(System.currentTimeMillis()));
			movieService.AddorUpdate(movie);
			return("redirect:/movies");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}	
	}
	@PostMapping("/edit/movie/{id}")
	public String editMovie(@PathVariable("id") Long id, Model model) {
		Movie movie = movieService.getMovieById(id);
		model.addAttribute("movie", movie);
		return ("update_movie");
	}
	@PostMapping("/deleteMovie/{id}")
	public String deleteMovieById(@PathVariable("id") Long id) {
		try {
			movieService.deleteMovie(id);
			return("redirect:/movies");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
