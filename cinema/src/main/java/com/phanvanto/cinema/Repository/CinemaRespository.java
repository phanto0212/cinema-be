package com.phanvanto.cinema.Repository;

import java.sql.Date;
import java.util.List;

import com.phanvanto.cinema.DTO.CinemaDTO;
import com.phanvanto.cinema.Entity.Cinema;

public interface CinemaRespository {

	List<Cinema> getList();
	void AddorUpdate(Cinema cinema);
	int deleteById(Long id);
	Cinema getCinemaById(Long id);
	List<Cinema> getAllCinemaByCity(String nameCity);
	List<CinemaDTO> getCinemasByMovieAndCity(Long movieId, Date dayShow, String city);
}
