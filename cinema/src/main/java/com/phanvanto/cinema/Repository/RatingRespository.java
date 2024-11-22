package com.phanvanto.cinema.Repository;


import java.util.List;

import com.phanvanto.cinema.Entity.Ratings;

public interface RatingRespository {
	List<Ratings > getAllratingByMovieId(Long movie_id);
	void deleteRatingById (Integer id);
	void AddorUpdate(Ratings rating);
}


