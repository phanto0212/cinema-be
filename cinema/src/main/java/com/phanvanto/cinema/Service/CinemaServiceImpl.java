package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		return cinemaRespository.deleteById(id);
	}

	@Override
	public Cinema getCinemaById(Long id) {
		// TODO Auto-generated method stub
		return cinemaRespository.getCinemaById(id);
	}

}
