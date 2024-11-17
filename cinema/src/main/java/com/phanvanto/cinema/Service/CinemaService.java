package com.phanvanto.cinema.Service;

import java.util.List;

import com.phanvanto.cinema.Entity.Cinema;

public interface CinemaService {
	List<Cinema> getList();
	void AddorUpdate(Cinema cinema);
	int deleteById(Long id);
	Cinema getCinemaById(Long id);
}
