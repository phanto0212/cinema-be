package com.phanvanto.cinema.Service;

import java.util.List;

import com.phanvanto.cinema.Entity.Seat;

public interface SeatService {

	List<Seat> getList();
	void AddorUpdate(Seat seat);
}
