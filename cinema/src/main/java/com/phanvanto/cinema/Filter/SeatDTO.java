package com.phanvanto.cinema.Filter;

public class SeatDTO {
    private Integer id;
    private Integer screen_id;
    private String seat_number;
    private String seat_type;
    private String status;
	public SeatDTO(Integer id, Integer screen_id, String seat_number, String seat_type, String status) {
		super();
		this.id = id;
		this.screen_id = screen_id;
		this.seat_number = seat_number;
		this.seat_type = seat_type;
		this.status = status;
	}
	public SeatDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getScreen_id() {
		return screen_id;
	}
	public void setScreen_id(Integer screen_id) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
