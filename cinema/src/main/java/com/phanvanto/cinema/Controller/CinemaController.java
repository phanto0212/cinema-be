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

import com.phanvanto.cinema.Entity.Cinema;
import com.phanvanto.cinema.Service.CinemaService;


@Controller
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;

	@GetMapping("/cinema")
	public String Cinema(Model model) {
		List<Cinema> cinemas = cinemaService.getList();
		model.addAttribute("cinemas", cinemas);
		return ("Cinema");
	}
	
	@PostMapping("/addOrUpdateCinema")
	public String AddorUpdateCinema(@ModelAttribute Cinema cinema) {
		if(cinema.getId()==null) {
			cinema.setCreated_at(new Timestamp(System.currentTimeMillis()));
			cinemaService.AddorUpdate(cinema);
			return ("redirect:/cinema");
		}
		else {
			cinema.setUpdated_at(new Timestamp(System.currentTimeMillis()));
			cinemaService.AddorUpdate(cinema);
			return ("redirect:/cinema");
		}
		
	}
	
	@PostMapping("/edit/cinema/{id}")
	public String editCinema(@PathVariable("id") Long id,Model model) {
		Cinema cinema = cinemaService.getCinemaById(id);
		model.addAttribute("cinema", cinema);
		return ("update_cinema");
	}
	
	
	@PostMapping("/delete/cinema/{id}")
	public String DeleteCinemaById(@PathVariable("id") Long id) {
		cinemaService.deleteById(id);
		return ("redirect:/cinema");
	}
}
