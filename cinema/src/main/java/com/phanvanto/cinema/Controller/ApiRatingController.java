package com.phanvanto.cinema.Controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phanvanto.cinema.Configs.JwtTokenUtil;
import com.phanvanto.cinema.DTO.MovieRateDTO;
import com.phanvanto.cinema.Entity.Ratings;
import com.phanvanto.cinema.Entity.User;
import com.phanvanto.cinema.Service.RatingService;
import com.phanvanto.cinema.Service.UserService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/rate")
public class ApiRatingController {
 
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	@PostMapping("/movie")
	public ResponseEntity<?> rateMovie(HttpServletRequest request,@RequestBody MovieRateDTO rateMovie){
		try {
			String jwt = request.getHeader("Authorization");
	        if (jwt == null || !jwt.startsWith("Bearer ")) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
	        }

	        jwt = jwt.substring(7);
	        Claims claims = jwtTokenUtil.getClaimsFromToken(jwt);
	        java.util.Date expiration = claims.getExpiration();
	        if (expiration.before(new java.util.Date())) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expired");
	        }

	        String username = claims.getSubject();
	        if (username == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
	        }

	        // Kiểm tra User
	        User user = userService.getUserByUsername(username);
	        if (user == null) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
	        }
	        List<Ratings> listRate = ratingService.getAllratingByMovieId(rateMovie.getMovieId());
	        for(Ratings rate : listRate) {
	        	if(rate.getUser().getId() == user.getId()) {
	        		rate.setRating(rateMovie.getRate());
	        		rate.setUpdated_at(new Timestamp(System.currentTimeMillis()));
	        		ratingService.AddorUpdate(rate);
	        		return ResponseEntity.ok("cap nhat thanh cong");
	        	}
	        }
	        Ratings ratings = new Ratings();
	        ratings.setMovie_id(rateMovie.getMovieId());
	        ratings.setRating(rateMovie.getRate());
	        ratings.setUser(user);
	        ratings.setCreated_at(new Timestamp(System.currentTimeMillis()));
	        ratingService.AddorUpdate(ratings);
	        return ResponseEntity.ok("them danh gia thanh cong");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@GetMapping("/get/rate/{id}")
	public ResponseEntity<?> getRate(@PathVariable("id") Long id){
		try {
			List<Ratings> rates = ratingService.getAllratingByMovieId(id);
			 // Tính trung bình
			int tong = 0;
			int count = rates.size();
			for (Ratings rate : rates) {
			    tong += rate.getRating();
			}

			// Tính giá trị trung bình và làm tròn
			Double tbrate = count > 0 ? (double) tong / count : 0;  // Nếu không có đánh giá thì trả về 0
			Double roundedRating = Math.round(tbrate * 10.0) / 10.0;  // Làm tròn đến 1 chữ số thập phân

            Map<String, Object> reponse = new HashMap<>();
            reponse.put("rate", roundedRating);
	        // Trả về kết quả
	        return ResponseEntity.ok(reponse);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
