package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.Seat;
import com.phanvanto.cinema.Filter.SeatDTO;
import com.phanvanto.cinema.Repository.SeatRespository;
@Service
public class SeatServiceImpl implements SeatService{

	@Autowired
	private SeatRespository seatRespository;
	@Override
	public List<Seat> getList() {
		// TODO Auto-generated method stub
		return seatRespository.getList();
	}

	@Override
	public void AddorUpdate(Seat seat) {
		seatRespository.AddorUpdate(seat);
		
	}

	@Override
	public void deleteSeatById(Long id) {
		seatRespository.deleteSeatById(id);;
		
	}

	@Override
	public List<SeatDTO> getAllSeatByShowtimeId(Long showtime_id) {
		return seatRespository.getAllSeatByShowtimeId(showtime_id);
	}

	@Override
	public List<Seat> getListByScreenId(Integer id) {
		return seatRespository.getListByScreenId(id) ;
	}

}
