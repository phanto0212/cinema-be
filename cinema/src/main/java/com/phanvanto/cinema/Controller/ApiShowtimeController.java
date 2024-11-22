package com.phanvanto.cinema.Controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phanvanto.cinema.Entity.Showtime;
import com.phanvanto.cinema.Service.ShowtimeService;

@RestController
@RequestMapping("/api/showtime")
public class ApiShowtimeController {
	@Autowired 
	private ShowtimeService showtimeService;
	
	@GetMapping("/get/all/showtime")
	public ResponseEntity<?> getAllShowtime(@RequestBody Map<String, String> params ){
		try {
			String CinemaIdStr = params.get("cinema_id");
			String MovieIdStr = params.get("movie_id");
			String dayShowStr = params.get("day_show");
			if(CinemaIdStr.isEmpty()|| MovieIdStr.isEmpty() || dayShowStr.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("thieu tham so nhe thg ngu");
			}
			 // Chuyển đổi cinema_id và movie_id từ String sang Long
	        Long cinemaId = Long.parseLong(CinemaIdStr);
	        Long movieId = Long.parseLong(MovieIdStr);

	        // Chuyển đổi day_show từ String sang java.sql.Date
	        Date dayShow = Date.valueOf(dayShowStr); // Định dạng phải là "yyyy-MM-dd"
	        List <Showtime> showtimes = showtimeService.getListShowtimebyCinema_idAndMovieId(cinemaId, movieId, dayShow);
	        Map<String, Object> reponse =  new HashMap<>();
	        reponse.put("showtimes", showtimes);
	        return ResponseEntity.ok(reponse);
	        
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
			
		}
		
	}

}
