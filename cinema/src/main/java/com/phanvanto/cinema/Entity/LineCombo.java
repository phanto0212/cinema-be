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
@Table(name = "line_combo")
public class LineCombo implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ticket_id")
	private Integer ticket_id;
	
	@Column(name = "combo_id")
	private Long combo_id;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "total_price", nullable = false, precision = 10, scale = 2)
	private BigDecimal total_price;

	public LineCombo(Long id, Integer ticket_id, Long combo_id, int quantity, BigDecimal total_price) {
		super();
		this.id = id;
		this.ticket_id = ticket_id;
		this.combo_id = combo_id;
		this.quantity = quantity;
		this.total_price = total_price;
	}

	public LineCombo() {
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

	public Long getCombo_id() {
		return combo_id;
	}

	public void setCombo_id(Long combo_id) {
		this.combo_id = combo_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotal_price() {
		return total_price;
	}

	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
