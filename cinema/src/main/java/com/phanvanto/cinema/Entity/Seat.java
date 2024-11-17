package com.phanvanto.cinema.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seat")
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "screen_id")
	private int screen_id;
	
	@Column(name = "seat_number")
	private String seat_number;
	
	@Column(name = "seat_type")
	private String seat_type;

	public Seat(Integer id, int screen_id, String seat_number, String seat_type) {
		super();
		this.id = id;
		this.screen_id = screen_id;
		this.seat_number = seat_number;
		this.seat_type = seat_type;
	}

	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getScreen_id() {
		return screen_id;
	}

	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}

	public String getSeat_number() {
		return seat_number;
	}

	public void setSeat_number(String seat_number) {
		this.seat_number = seat_number;
	}

	public String getSeat_type() {
		return seat_type;
	}

	public void setSeat_type(String seat_type) {
		this.seat_type = seat_type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
