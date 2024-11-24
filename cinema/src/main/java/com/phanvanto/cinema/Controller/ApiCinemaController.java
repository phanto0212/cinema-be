package com.phanvanto.cinema.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.phanvanto.cinema.DTO.CinemaDTO;
import com.phanvanto.cinema.Entity.Cinema;
import com.phanvanto.cinema.Service.CinemaService;
import com.phanvanto.cinema.Service.ShowtimeService;

@RestController
@RequestMapping("/api/cinema")
public class ApiCinemaController {

	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private ShowtimeService showtimeService;
	
	@PostMapping("/get/all/city")
	public ResponseEntity<?> getAllByCity(@RequestBody Map<String, String> params) throws Exception{
		try {
			String movie_id_STR = params.get("movie_id");
			Long movie_id = null;
			if (movie_id_STR != null && !movie_id_STR.isEmpty()) {
			    try {
			        movie_id = Long.parseLong(movie_id_STR);
			    } catch (NumberFormatException e) {
			        // Xử lý khi chuỗi không thể chuyển thành Long
			        System.out.println("Lỗi khi chuyển đổi movie_id: " + e.getMessage());
			    }
			}
			String CityName = params.get("city");
			String dayBookStr = params.get("day_book");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate = dateFormat.parse(dayBookStr);
			Date dayBook = new Date(utilDate.getTime());
			
			if(CityName == "") {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City rỗng!!");
			}
			List<CinemaDTO> cinemas = cinemaService.getCinemasByMovieAndCity(movie_id, dayBook, CityName);

//			List<Showtime> showtimes = showtimeService
			Map<String, Object> reponse = new HashMap<>();
			reponse.put("cinemas", cinemas);
			return ResponseEntity.ok(reponse);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
}
