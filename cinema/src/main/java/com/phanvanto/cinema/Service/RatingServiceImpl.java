package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.Ratings;
import com.phanvanto.cinema.Repository.RatingRespository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRespository ratingRespository;
	@Override
	public List<Ratings> getAllratingByMovieId(Long movie_id) {
		return ratingRespository.getAllratingByMovieId(movie_id);
	}

	@Override
	public void deleteRatingById(Integer id) {
		ratingRespository.deleteRatingById(id);
		
	}

	@Override
	public void AddorUpdate(Ratings rating) {
		ratingRespository.AddorUpdate(rating);
		
	}

}
