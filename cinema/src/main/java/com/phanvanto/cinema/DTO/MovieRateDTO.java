package com.phanvanto.cinema.DTO;

public class MovieRateDTO {
 
	private Long movieId;
	private int rate;
	public MovieRateDTO(Long movieId, int rate) {
		super();
		this.movieId = movieId;
		this.rate = rate;
	}
	public MovieRateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
}
