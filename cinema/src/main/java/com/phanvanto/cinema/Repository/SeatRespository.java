package com.phanvanto.cinema.Repository;

import java.util.List;

import com.phanvanto.cinema.Entity.Seat;
import com.phanvanto.cinema.Filter.SeatDTO;

public interface SeatRespository {

	List<Seat> getList();
	List<SeatDTO> getAllSeatByShowtimeId(Long showtime_id);
	List<Seat> getListByScreenId(Integer id);
	void AddorUpdate(Seat seat);
    void deleteSeatById(Long id);
}
