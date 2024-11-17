package com.phanvanto.cinema.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "screen")
public class Screen implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "cinema_id")
	private Long cinema_id;
	
	@Column(name = "screen_number")
	private String screen_number;
	
	@Column(name = "total_seats")
	private int total_seat;

	public Screen(Integer id, Long cinema_id, String screen_number, int total_seat) {
		super();
		this.id = id;
		this.cinema_id = cinema_id;
		this.screen_number = screen_number;
		this.total_seat = total_seat;
	}

	public Screen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCinema_id() {
		return cinema_id;
	}

	public void setCinema_id(Long cinema_id) {
		this.cinema_id = cinema_id;
	}

	public String getScreen_number() {
		return screen_number;
	}

	public void setScreen_number(String screen_number) {
		this.screen_number = screen_number;
	}

	public int getTotal_seat() {
		return total_seat;
	}

	public void setTotal_seat(int total_seat) {
		this.total_seat = total_seat;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
