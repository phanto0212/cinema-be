package com.phanvanto.cinema.Controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phanvanto.cinema.Configs.JwtTokenUtil;
import com.phanvanto.cinema.DTO.ComboDTO;
import com.phanvanto.cinema.Entity.Cinema;
import com.phanvanto.cinema.Entity.Combo;
import com.phanvanto.cinema.Entity.LineCombo;
import com.phanvanto.cinema.Entity.Line_Ticket;
import com.phanvanto.cinema.Entity.Movie;
import com.phanvanto.cinema.Entity.Screen;
import com.phanvanto.cinema.Entity.Showtime;
import com.phanvanto.cinema.Entity.Ticket;
import com.phanvanto.cinema.Entity.User;
import com.phanvanto.cinema.Request.TicketRequest;
import com.phanvanto.cinema.Service.CinemaService;
import com.phanvanto.cinema.Service.ComboService;
import com.phanvanto.cinema.Service.LineComboService;
import com.phanvanto.cinema.Service.LineTicketService;
import com.phanvanto.cinema.Service.MovieService;
import com.phanvanto.cinema.Service.ScreenService;
import com.phanvanto.cinema.Service.ShowtimeService;
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
	private ShowtimeService showtimeService;
	
	@Autowired
	private ScreenService screenService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private LineTicketService lineTicketService;
	
	@Autowired
	private LineComboService lineComboService;
	
	@Autowired 
	private ComboService comboService;
	
	@Autowired 
	private CinemaService cinemaService;
	
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
		        ticket.setPurchase_time(new Timestamp(System.currentTimeMillis()));
		        ticketService.AddorUpdate(ticket);
		        for(Integer id : params.getSeatIds()) {
		        	Line_Ticket line_ticket = new Line_Ticket();
		        	line_ticket.setSeat_id(id);
		        	line_ticket.setTicket_id(ticket.getTicket_id());
		        	line_ticket.setPrice(totalPrice);
		        	lineTicketService.AddorUpdate(line_ticket);
		        }
		        List<Combo> combos = comboService.getList();
		        for (ComboDTO combo : params.getComboIds() ) {
		        	LineCombo lineCombo =  new LineCombo();
		        	lineCombo.setCombo_id(combo.getCombo_id());
                    lineCombo.setQuantity(combo.getQuantity());
                    lineCombo.setTicket_id(ticket.getTicket_id());
                    for(Combo eachCombo : combos ) {
                    	if(eachCombo.getId()==combo.getCombo_id()) {
                    		lineCombo.setTotal_price(eachCombo.getPrice().multiply(BigDecimal.valueOf(combo.getQuantity())));
                    	}
                    }
                    lineComboService.AddorUpdate(lineCombo);
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
	@PostMapping("/get/info/{id}")
	public ResponseEntity<?> getInfo(@PathVariable("id") Long id){
		try {
			Ticket ticket = ticketService.getTicketById(id);
			Showtime showtime = showtimeService.getShowTimebyShowtime_Id(ticket.getShowtime_id());
			Movie movie = movieService.getMovieById(showtime.getMovie_id());
			Screen screen = screenService.getScreenById(showtime.getScreen_id());
			List<Line_Ticket> line_tickets = lineTicketService.getListByTicketId(id);
			List<LineCombo> line_combos = lineComboService.getListByticketId(id);
			Cinema cinema = cinemaService.getCinemaById(showtime.getCinema_id());
			Map<String, Object> reponse = new HashMap<>();
			reponse.put("cinema", cinema);
			reponse.put("showtime", showtime);
			reponse.put("movie", movie);
			reponse.put("line_tickets", line_tickets);
			reponse.put("screen", screen);
			reponse.put("line_combos", line_combos);
			return ResponseEntity.ok(reponse);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
