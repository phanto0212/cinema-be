package com.phanvanto.cinema.Service;

import java.util.List;

import com.phanvanto.cinema.Entity.Seat;
import com.phanvanto.cinema.Filter.SeatDTO;

public interface SeatService {

	List<Seat> getList();
	List<SeatDTO> getAllSeatByShowtimeId(Long showtime_id);
	void AddorUpdate(Seat seat);
	void deleteSeatById(Long id);
}
