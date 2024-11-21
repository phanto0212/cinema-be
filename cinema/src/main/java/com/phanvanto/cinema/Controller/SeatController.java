package com.phanvanto.cinema.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.phanvanto.cinema.Entity.Seat;
import com.phanvanto.cinema.Filter.SeatDTO;
import com.phanvanto.cinema.Service.SeatService;

@Controller
public class SeatController {

	@Autowired
	private SeatService seatService;
	
	@GetMapping("/seat")
	public String Seat(Model model) {
		List<Seat> seats = seatService.getList();
		
		model.addAttribute("seats", seats);
		return ("Seat");
		
	}
	@PostMapping("add/seat/{id}")
	public String addSeat(@PathVariable("id") int id) {
        char[] rows = "ABCDEFGH".toCharArray();
        int seatPerRow = 20; 
        for (char row : rows) {
            for (int i = 1; i <= seatPerRow; i++) {
                String seatNumber = row + String.format("%02d", i);
                Seat seat = new Seat();
                seat.setScreen_id(id);
                seat.setSeat_number(seatNumber);
                seat.setStatus("available");
                seat.setSeat_type("standard");
                seatService.AddorUpdate(seat);
            }
        }
		return("redirect:/seat");
	}
}
