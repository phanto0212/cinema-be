package com.phanvanto.cinema.DTO;

public class ComboConfirmDTO {

	private String name;
	private int quantity;
	public ComboConfirmDTO(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}
	public ComboConfirmDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	
}
