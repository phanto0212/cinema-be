package com.phanvanto.cinema.DTO;

import java.util.List;

import com.phanvanto.cinema.Entity.Cinema;
import com.phanvanto.cinema.Entity.Movie;
import com.phanvanto.cinema.Entity.Screen;
import com.phanvanto.cinema.Entity.Showtime;
import com.phanvanto.cinema.Entity.Ticket;

public class MyTicketDTO {

	private Cinema cinema;
	private Showtime showtime;
	private Movie movie;
	private Screen screen;
	private List<ComboDTO> combo_ticket;
	private List<String> listNameSeat;
	private Ticket ticket;
	public MyTicketDTO(Cinema cinema, Showtime showtime, Movie movie, Screen screen, List<ComboDTO> combo_ticket,
			List<String> listNameSeat, Ticket ticket) {
		super();
		this.cinema = cinema;
		this.showtime = showtime;
		this.movie = movie;
		this.screen = screen;
		this.combo_ticket = combo_ticket;
		this.listNameSeat = listNameSeat;
		this.ticket = ticket;
	}
	public MyTicketDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public Showtime getShowtime() {
		return showtime;
	}
	public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Screen getScreen() {
		return screen;
	}
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	public List<ComboDTO> getCombo_ticket() {
		return combo_ticket;
	}
	public void setCombo_ticket(List<ComboDTO> combo_ticket) {
		this.combo_ticket = combo_ticket;
	}
	public List<String> getListNameSeat() {
		return listNameSeat;
	}
	public void setListNameSeat(List<String> listNameSeat) {
		this.listNameSeat = listNameSeat;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
}
