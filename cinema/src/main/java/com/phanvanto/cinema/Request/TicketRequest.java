package com.phanvanto.cinema.Request;

import java.util.List;

import com.phanvanto.cinema.DTO.ComboDTO;

public class TicketRequest {
    private Long movieId;
    private Long showtimeId;
    private int screenId;
    private int countAdult;
    private int countChild;
    private List<Integer> seatIds;
    private List<ComboDTO> comboIds;
	public TicketRequest(Long movieId, Long showtimeId, int screenId, int countAdult, int countChild,
			List<Integer> seatIds, List<ComboDTO> comboIds) {
		super();
		this.movieId = movieId;
		this.showtimeId = showtimeId;
		this.screenId = screenId;
		this.countAdult = countAdult;
		this.countChild = countChild;
		this.seatIds = seatIds;
		this.comboIds = comboIds;
	}
	public TicketRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public Long getShowtimeId() {
		return showtimeId;
	}
	public void setShowtimeId(Long showtimeId) {
		this.showtimeId = showtimeId;
	}
	public int getScreenId() {
		return screenId;
	}
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
	public int getCountAdult() {
		return countAdult;
	}
	public void setCountAdult(int countAdult) {
		this.countAdult = countAdult;
	}
	public int getCountChild() {
		return countChild;
	}
	public void setCountChild(int countChild) {
		this.countChild = countChild;
	}
	public List<Integer> getSeatIds() {
		return seatIds;
	}
	public void setSeatIds(List<Integer> seatIds) {
		this.seatIds = seatIds;
	}
	public List<ComboDTO> getComboIds() {
		return comboIds;
	}
	public void setComboIds(List<ComboDTO> comboIds) {
		this.comboIds = comboIds;
	}
	
	
}