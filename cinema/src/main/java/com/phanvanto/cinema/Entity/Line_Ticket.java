package com.phanvanto.cinema.Entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "line_ticket")
public class Line_Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name  = "ticket_id", nullable = false)
	private Integer ticket_id;
	
	@Column(name = "seat_id", nullable = false)
	private Integer seat_id;
	
	@Column(name = "price", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	public Line_Ticket(Long id, Integer ticket_id, Integer seat_id, BigDecimal price) {
		super();
		this.id = id;
		this.ticket_id = ticket_id;
		this.seat_id = seat_id;
		this.price = price;
	}

	public Line_Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(Integer ticket_id) {
		this.ticket_id = ticket_id;
	}

	public Integer getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(Integer seat_id) {
		this.seat_id = seat_id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
