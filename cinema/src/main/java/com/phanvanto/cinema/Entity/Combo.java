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
@Table(name = "combos")
public class Combo implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "avt_url")
	private String avt_url;
	
	@Column(name = "price", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	public Combo(Long id, String name, String description, String avt_url, BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.avt_url = avt_url;
		this.price = price;
	}

	public Combo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvt_url() {
		return avt_url;
	}

	public void setAvt_url(String avt_url) {
		this.avt_url = avt_url;
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
