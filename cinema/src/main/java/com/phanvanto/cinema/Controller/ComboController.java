package com.phanvanto.cinema.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.phanvanto.cinema.Entity.Combo;
import com.phanvanto.cinema.Service.ComboService;

@Controller
public class ComboController {

	@Autowired
	private ComboService comboService;
	
	@GetMapping("/combo")
	public String Combo(Model model) {
		List<Combo> combos = comboService.getList();
		model.addAttribute("combos", combos);
		return("Combo");
	}
	@PostMapping("/addOrUpdateCombo")
	public String addorUpdate(@ModelAttribute Combo combo) {
		comboService.AddorUpdate(combo);
		return ("redirect:/combo");
	}
}
