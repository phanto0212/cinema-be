package com.phanvanto.cinema.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.phanvanto.cinema.Entity.Ticket;
import com.phanvanto.cinema.Service.TicketService;

@Controller
public class TicketController {

	@Autowired 
	private TicketService ticketService;
	
	@GetMapping("/ticket")
	public String Ticket(Model model) {
		List<Ticket> tickets = ticketService.getList();
		model.addAttribute("tickets", tickets);
		return("Ticket");
		
	}

	@PostMapping("/addOrUpdateticket")
	public String AddorUpdate(@ModelAttribute Ticket ticket) {
		ticketService.AddorUpdate(ticket);
		return("redirect:/ticket");
	}
}
