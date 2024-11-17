package com.phanvanto.cinema.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.phanvanto.cinema.Service.ScreenService;
import com.phanvanto.cinema.Entity.Screen;

@Controller
public class ScreenController {

	@Autowired
	private ScreenService screenService;
	
	@GetMapping("/screen")
	public String Screen(Model model) {
		List<Screen> screens = screenService.getList();
		model.addAttribute("screens", screens);
		return("Screen");
	}
	@PostMapping("/addOrUpdateScreen")
	public String AddorUpdate(@ModelAttribute Screen screen) {
		screenService.AddorUpdate(screen);
		return "redirect:/screen";
	}
	@PostMapping("/edit/screen/{id}")
	public String editScreen(@PathVariable("id") Long id,Model model) {
		Screen screen = screenService.getScreenById(id);
		model.addAttribute("screen", screen);
		return("update_screen");
	}
	@PostMapping("/add/screen/{id}")
	public String addScreen(@PathVariable("id") Long id, Model model) {
		Screen screen = new Screen();
		screen.setCinema_id(id);
		model.addAttribute("screen", screen);
		return("add_screen");
		
	}
}
