package com.phanvanto.cinema.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "text_description")
	private String text_description;
	
	@Column(name = "director")
	private String director;
	
	@Column(name = "release_date")
	private Date release_date;
	
	@Column(name = "duration")
	private int duration;
	
	@Column(name = "kind")
	private String kind;
	
	@Column(name = "poster_url")
	private String poster_url;
	
	@Column(name = "created_at")
	private Timestamp created_at;
	
	@Column(name = "updated_at")
	private Timestamp updated_at;
	
	@Column(name = "adult_price", nullable = false, precision = 10, scale = 2)
	private BigDecimal adult_price;

	@Column(name = "child_price", nullable = false, precision = 10, scale = 2)
	private BigDecimal child_price;
	
	@Column(name = "actors")
	private String actors;
	
	@Column(name = "trailer_url")
	private String trailer_url;

	public Movie(Long id, String title, String text_description, String director, Date release_date, int duration,
			String kind, String poster_url, Timestamp created_at, Timestamp updated_at, BigDecimal adult_price,
			BigDecimal child_price, String actors, String trailer_url) {
		super();
		this.id = id;
		this.title = title;
		this.text_description = text_description;
		this.director = director;
		this.release_date = release_date;
		this.duration = duration;
		this.kind = kind;
		this.poster_url = poster_url;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.adult_price = adult_price;
		this.child_price = child_price;
		this.actors = actors;
		this.trailer_url = trailer_url;
	}

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText_description() {
		return text_description;
	}

	public void setText_description(String text_description) {
		this.text_description = text_description;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getPoster_url() {
		return poster_url;
	}

	public void setPoster_url(String poster_url) {
		this.poster_url = poster_url;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public BigDecimal getAdult_price() {
		return adult_price;
	}

	public void setAdult_price(BigDecimal adult_price) {
		this.adult_price = adult_price;
	}

	public BigDecimal getChild_price() {
		return child_price;
	}

	public void setChild_price(BigDecimal child_price) {
		this.child_price = child_price;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getTrailer_url() {
		return trailer_url;
	}

	public void setTrailer_url(String trailer_url) {
		this.trailer_url = trailer_url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
