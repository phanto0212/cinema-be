package com.phanvanto.cinema.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.phanvanto.cinema.Entity.Line_Ticket;
import com.phanvanto.cinema.Service.LineTicketService;

@Controller
public class LineTicketController {

	@Autowired
	private LineTicketService lineTicketService;
	
	@GetMapping("/line_ticket")
	public String LineTicket(Model model) {
		List<Line_Ticket> linetickets = lineTicketService.getList();
		model.addAttribute("linetickets", linetickets);
		return("Lineticket");
	}
	@PostMapping("/addOrUpdateLineticket")
	public String AddorUpdate(@ModelAttribute Line_Ticket line_ticket) {
		lineTicketService.AddorUpdate(line_ticket);
		return("redirect:/line_ticket");
	}
	@PostMapping("/delete/lineticket/{id}")
	public String delete(@PathVariable("id") Long id) {
		lineTicketService.deleteById(id);
		return("redirect:/line_ticket");
	}
	
	
	
}
