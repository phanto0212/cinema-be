package com.phanvanto.cinema.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ratings")
public class Ratings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@Column(name = "rating_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rating_id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;
    
    @Column(name = "movie_id")
    private Long movie_id;
    
    @Column(name = "rating")
    private int rating;
    
    @Column(name = "updated_at")
    private Timestamp updated_at;
    
    @Column(name = "created_at")
    private Timestamp created_at;

	public Ratings(Integer rating_id, User user, Long movie_id, int rating, Timestamp updated_at,
			Timestamp created_at) {
		super();
		this.rating_id = rating_id;
		this.user = user;
		this.movie_id = movie_id;
		this.rating = rating;
		this.updated_at = updated_at;
		this.created_at = created_at;
	}

	public Ratings() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRating_id() {
		return rating_id;
	}

	public void setRating_id(Integer rating_id) {
		this.rating_id = rating_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
