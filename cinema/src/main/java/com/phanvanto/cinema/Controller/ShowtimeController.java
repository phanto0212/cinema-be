package com.phanvanto.cinema.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.phanvanto.cinema.Entity.Cinema;
import com.phanvanto.cinema.Entity.Movie;
import com.phanvanto.cinema.Entity.Screen;
import com.phanvanto.cinema.Entity.Showtime;
import com.phanvanto.cinema.Service.CinemaService;
import com.phanvanto.cinema.Service.MovieService;
import com.phanvanto.cinema.Service.ScreenService;
import com.phanvanto.cinema.Service.ShowtimeService;

@Controller
public class ShowtimeController {

	@Autowired 
	private ShowtimeService showtimeService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private ScreenService screenService;
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/showtime")
	public String Showtime(Model model) {
		List<Showtime> showtimes = showtimeService.getList();
		List<Cinema> cinemas = cinemaService.getList();
		List<Screen> screens = screenService.getList();
		List<Movie> movies = movieService.getlist();
		model.addAttribute("showtimes", showtimes);
		model.addAttribute("cinemas", cinemas);
		model.addAttribute("screens", screens);
		model.addAttribute("movies", movies);
		return("Showtime");
	}
	@PostMapping("/addOrUpdateShowtime")
	public String addorUpdate(@ModelAttribute Showtime showtime) {
		try {
			showtimeService.AddorUpdate(showtime);
			return ("redirect:/showtime");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@PostMapping("/deleteShowtime/{id}")
	public String deleteShowtime(@PathVariable("id") Long id) {
		try {
			showtimeService.DeleteById(id);
			return ("redirect:/showtime");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@PostMapping("/edit/showtime/{id}")
	public String editShowtime(@PathVariable("id") Long id, Model model) {
		Showtime showtime = showtimeService.getShowTimebyShowtime_Id(id);
		List<Cinema> cinemas = cinemaService.getList();
		List<Screen> screens = screenService.getList();
		List<Movie> movies = movieService.getlist();
		model.addAttribute("cinemas", cinemas);
		model.addAttribute("screens", screens);
		model.addAttribute("movies", movies);
		model.addAttribute("showtime", showtime);
		return("update_showtime");
	}
}
