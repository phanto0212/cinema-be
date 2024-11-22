package com.phanvanto.cinema.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.DTO.CinemaDTO;
import com.phanvanto.cinema.Entity.Cinema;
import com.phanvanto.cinema.Repository.CinemaRespository;
@Service
public class CinemaServiceImpl implements CinemaService {

	@Autowired
	private CinemaRespository cinemaRespository;
	
	@Override
	public List<Cinema> getList() {
		return cinemaRespository.getList();
	}

	@Override
	public void AddorUpdate(Cinema cinema) {
		cinemaRespository.AddorUpdate(cinema);
		
	}

	@Override
	public int deleteById(Long id) {
		return cinemaRespository.deleteById(id);
	}

	@Override
	public Cinema getCinemaById(Long id) {
		return cinemaRespository.getCinemaById(id);
	}

	@Override
	public List<Cinema> getAllCinemaByCity(String nameCity) {
		return cinemaRespository.getAllCinemaByCity(nameCity);
	}

	@Override
	public List<CinemaDTO> getCinemasByMovieAndCity(Long movieId, Date dayShow, String city) {
		return cinemaRespository.getCinemasByMovieAndCity(movieId, dayShow, city);
	}

}
