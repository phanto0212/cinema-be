package com.phanvanto.cinema.DTO;

import java.math.BigDecimal;

public class ComboDTO {

	private Long combo_id;
	private String name;
	private int quantity;
	private BigDecimal price;
	public ComboDTO(Long combo_id, String name, int quantity, BigDecimal price) {
		super();
		this.combo_id = combo_id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	public ComboDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getCombo_id() {
		return combo_id;
	}
	public void setCombo_id(Long combo_id) {
		this.combo_id = combo_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
