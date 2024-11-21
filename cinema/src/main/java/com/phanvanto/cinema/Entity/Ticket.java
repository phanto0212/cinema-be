package com.phanvanto.cinema.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@Column(name = "ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticket_id;
    
    @Column(name = "showtime_id")
    private Long showtime_id;
    
    @Column(name = "movie_id")
    private Long movie_id;
    
    @Column(name = "screen_id")
    private int screen_id;
    
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(name = "purchase_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Timestamp purchase_time;
    
    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Timestamp start_time;
    
    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Timestamp end_time;
    
    @Column(name = "status")
    private String status;

	public Ticket(Integer ticket_id, Long showtime_id, Long movie_id, int screen_id, BigDecimal price,
			Timestamp purchase_time, Timestamp start_time, Timestamp end_time, String status) {
		super();
		this.ticket_id = ticket_id;
		this.showtime_id = showtime_id;
		this.movie_id = movie_id;
		this.screen_id = screen_id;
		this.price = price;
		this.purchase_time = purchase_time;
		this.start_time = start_time;
		this.end_time = end_time;
		this.status = status;
	}

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(Integer ticket_id) {
		this.ticket_id = ticket_id;
	}

	public Long getShowtime_id() {
		return showtime_id;
	}

	public void setShowtime_id(Long showtime_id) {
		this.showtime_id = showtime_id;
	}

	public Long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}

	public int getScreen_id() {
		return screen_id;
	}

	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Timestamp getPurchase_time() {
		return purchase_time;
	}

	public void setPurchase_time(Timestamp purchase_time) {
		this.purchase_time = purchase_time;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
