package com.phanvanto.cinema.Controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.phanvanto.cinema.DTO.MyTicketDTO;
import com.phanvanto.cinema.Entity.Cinema;
import com.phanvanto.cinema.Entity.Combo;
import com.phanvanto.cinema.Entity.LineCombo;
import com.phanvanto.cinema.Entity.Line_Ticket;
import com.phanvanto.cinema.Entity.Movie;
import com.phanvanto.cinema.Entity.Screen;
import com.phanvanto.cinema.Entity.Seat;
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
import com.phanvanto.cinema.Service.SeatService;
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
	
	@Autowired
	private SeatService seatService;
	
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
		        BigDecimal totalPrice = params.getTotalPrice();
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
			List<Seat> seats = seatService.getListByScreenId(screen.getId());
			List<Combo> combos = comboService.getList();
			List<String> listNameSeat = new ArrayList<>();
			for (Line_Ticket line_ticket : line_tickets) {
			    for (Seat seat : seats) {
			        
			        if (line_ticket.getSeat_id().longValue() == seat.getId().longValue()) {			        
			            listNameSeat.add(seat.getSeat_number());
			        }
			    }
			}
			List<ComboDTO> combo_ticket =  new ArrayList<>();
			List<LineCombo> line_combos = lineComboService.getListByticketId(id);
			for(LineCombo line_combo : line_combos) {
				for(Combo combo : combos) {
					if(combo.getId() == line_combo.getCombo_id())
					{
						ComboDTO comboDTO = new ComboDTO();
						comboDTO.setCombo_id(combo.getId());
						comboDTO.setName(combo.getName());
						comboDTO.setQuantity(line_combo.getQuantity());
						comboDTO.setPrice(combo.getPrice());
						combo_ticket.add(comboDTO);
						
					}
				}
			}
			Cinema cinema = cinemaService.getCinemaById(showtime.getCinema_id());
			Map<String, Object> reponse = new HashMap<>();
			reponse.put("cinema", cinema);
			reponse.put("showtime", showtime);
			reponse.put("movie", movie);
			reponse.put("line_tickets", listNameSeat);
			reponse.put("screen", screen);
			reponse.put("line_combos", combo_ticket);
			reponse.put("ticket", ticket);
			return ResponseEntity.ok(reponse);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	@PostMapping("/get/all/ticket/user")
	public ResponseEntity<?> getAllTicketToken(HttpServletRequest request){
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
	        List<Ticket> tickets = ticketService.getAllTicketByUserId(user.getId());
	        List<MyTicketDTO> listTicket = new ArrayList<>();
	        if(tickets.size() < 1) {
	        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("khong co gi ca");
	        }
	        for(Ticket ticket : tickets) {
	        	Showtime showtime = showtimeService.getShowTimebyShowtime_Id(ticket.getShowtime_id());
				Movie movie = movieService.getMovieById(showtime.getMovie_id());
				Screen screen = screenService.getScreenById(showtime.getScreen_id());
				List<Line_Ticket> line_tickets = lineTicketService.getListByTicketId(ticket.getTicket_id().longValue());
				List<Seat> seats = seatService.getListByScreenId(screen.getId());
				List<Combo> combos = comboService.getList();
				List<String> listNameSeat = new ArrayList<>();
				for (Line_Ticket line_ticket : line_tickets) {
				    for (Seat seat : seats) {
				        
				        if (line_ticket.getSeat_id().longValue() == seat.getId().longValue()) {			        
				            listNameSeat.add(seat.getSeat_number());
				        }
				    }
				}
				List<ComboDTO> combo_ticket =  new ArrayList<>();
				List<LineCombo> line_combos = lineComboService.getListByticketId(ticket.getTicket_id().longValue());
				for(LineCombo line_combo : line_combos) {
					for(Combo combo : combos) {
						if(combo.getId() == line_combo.getCombo_id())
						{
							ComboDTO comboDTO = new ComboDTO();
							comboDTO.setCombo_id(combo.getId());
							comboDTO.setName(combo.getName());
							comboDTO.setQuantity(line_combo.getQuantity());
							comboDTO.setPrice(combo.getPrice());
							combo_ticket.add(comboDTO);
							
						}
					}
				}
				Cinema cinema = cinemaService.getCinemaById(showtime.getCinema_id());
				MyTicketDTO myticketDTO = new MyTicketDTO();
				myticketDTO.setCinema(cinema);
				myticketDTO.setCombo_ticket(combo_ticket);
				myticketDTO.setListNameSeat(listNameSeat);
				myticketDTO.setMovie(movie);
				myticketDTO.setScreen(screen);
				myticketDTO.setShowtime(showtime);
				myticketDTO.setTicket(ticket);
				listTicket.add(myticketDTO);
				
	        }
	        Map<String, Object> reponse = new HashMap<>();
	        reponse.put("tickets", listTicket);
	        return ResponseEntity.ok(reponse);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
