package com.phanvanto.cinema.DTO;
import java.sql.Date;
import java.time.LocalTime;

public class CinemaDTO {
    private Long cinemaId;
    private String cinemaName;
    private String address;
    private Long movieId;
    private LocalTime timeShow;
    private Date dayShow;
	public CinemaDTO(Long cinemaId, String cinemaName, String address, Long movieId, LocalTime timeShow, Date dayShow) {
		super();
		this.cinemaId = cinemaId;
		this.cinemaName = cinemaName;
		this.address = address;
		this.movieId = movieId;
		this.timeShow = timeShow;
		this.dayShow = dayShow;
	}
	public CinemaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(Long cinemaId) {
		this.cinemaId = cinemaId;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public LocalTime getTimeShow() {
		return timeShow;
	}
	public void setTimeShow(LocalTime timeShow) {
		this.timeShow = timeShow;
	}
	public Date getDayShow() {
		return dayShow;
	}
	public void setDayShow(Date dayShow) {
		this.dayShow = dayShow;
	}
    
	
}