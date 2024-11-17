package com.phanvanto.cinema.Repository;

import java.util.List;

import com.phanvanto.cinema.Entity.Cinema;

public interface CinemaRespository {

	List<Cinema> getList();
	void AddorUpdate(Cinema cinema);
	int deleteById(Long id);
	Cinema getCinemaById(Long id);
	
}
