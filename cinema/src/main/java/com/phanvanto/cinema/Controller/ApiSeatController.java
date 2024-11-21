package com.phanvanto.cinema.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phanvanto.cinema.Filter.SeatDTO;
import com.phanvanto.cinema.Service.SeatService;

@RestController
@RequestMapping("/api/seat")
public class ApiSeatController {

	@Autowired
	private SeatService seatService;
	
	@GetMapping("/get/by/showtime/{id}")
	public ResponseEntity<?> getSeatByShowtime_id(@PathVariable("id") Long id){
		List<SeatDTO> seats = seatService.getAllSeatByShowtimeId(id);
		Map<String, Object> reponse = new HashMap();
		reponse.put("seats", seats);
		return ResponseEntity.ok(reponse);
	}
	
}
