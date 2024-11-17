package com.phanvanto.cinema.Repository;

import java.util.List;

import com.phanvanto.cinema.Entity.Seat;

public interface SeatRespository {

	List<Seat> getList();
	void AddorUpdate(Seat seat);

}
