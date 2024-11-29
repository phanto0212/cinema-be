package com.phanvanto.cinema.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phanvanto.cinema.Configs.JwtTokenUtil;
import com.phanvanto.cinema.Entity.Line_Ticket;
import com.phanvanto.cinema.Entity.Movie;
import com.phanvanto.cinema.Entity.Ticket;
import com.phanvanto.cinema.Entity.User;
import com.phanvanto.cinema.Request.TicketRequest;
import com.phanvanto.cinema.Service.LineTicketService;
import com.phanvanto.cinema.Service.MovieService;
import com.phanvanto.cinema.Service.TicketService;
import com.phanvanto.cinema.Service.UserService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/ticket")
public class ApiTicketController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private LineTicketService lineTicketService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@PostMapping("/order/ticket")
	public ResponseEntity<?> orderticket(HttpServletRequest request,@RequestBody TicketRequest params){
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
		        Movie movie = movieService.getMovieById(params.getMovieId());
		        if(movie== null) {
		        	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("movie khong ton tai"+params.getMovieId());
		        }
		        BigDecimal adultPrice = movie.getAdult_price();
		        BigDecimal childPrice = movie.getChild_price();
		        BigDecimal countAdult = BigDecimal.valueOf(params.getCountAdult());
		        BigDecimal countChild = BigDecimal.valueOf(params.getCountChild());

		        // Tính tổng giá
		        BigDecimal totalPrice = countAdult.multiply(adultPrice).add(countChild.multiply(childPrice));
		        Ticket ticket = new Ticket();
		        ticket.setUser_id(user.getId());
		        ticket.setStatus("confimed");
		        ticket.setPrice(totalPrice);
		        ticket.setMovie_id(params.getMovieId());
		        ticket.setScreen_id(params.getScreenId());
		        ticket.setShowtime_id(params.getShowtimeId());
		        ticketService.AddorUpdate(ticket);
		        for(Integer id : params.getSeatIds()) {
		        	Line_Ticket line_ticket = new Line_Ticket();
		        	line_ticket.setSeat_id(id);
		        	line_ticket.setTicket_id(ticket.getTicket_id());
		        	line_ticket.setPrice(totalPrice);
		        	lineTicketService.AddorUpdate(line_ticket);
		        }
		        
		        Map<String, Object> reponse = new HashMap<>();
		        reponse.put("ticket_id", ticket.getTicket_id());
		        return ResponseEntity.ok(reponse);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
			
		}
		
	}
}
