package com.phanvanto.cinema.Service;

import java.util.List;

import com.phanvanto.cinema.Entity.Ratings;

public interface RatingService {
	List<Ratings > getAllratingByMovieId(Long movie_id);
	void deleteRatingById (Integer id);
	void AddorUpdate(Ratings rating);
}
