package com.phanvanto.cinema.Entity;

import java.io.Serializable;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "showtimes")
public class Showtime implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cinema_id")
	private Long cinema_id;
	
	@Column(name = "movie_id")
	private Long movie_id;
	
	@Column(name = "time_show")
	private LocalTime time_show;

	public Showtime(Long id, Long cinema_id, Long movie_id, LocalTime time_show) {
		super();
		this.id = id;
		this.cinema_id = cinema_id;
		this.movie_id = movie_id;
		this.time_show = time_show;
	}

	public Showtime() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCinema_id() {
		return cinema_id;
	}

	public void setCinema_id(Long cinema_id) {
		this.cinema_id = cinema_id;
	}

	public Long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}

	public LocalTime getTime_show() {
		return time_show;
	}

	public void setTime_show(LocalTime time_show) {
		this.time_show = time_show;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
