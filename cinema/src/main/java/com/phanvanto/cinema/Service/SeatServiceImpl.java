package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.Seat;
import com.phanvanto.cinema.Repository.SeatRespository;
@Service
public class SeatServiceImpl implements SeatService{

	@Autowired
	private SeatRespository seatRespository;
	@Override
	public List<Seat> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void AddorUpdate(Seat seat) {
		// TODO Auto-generated method stub
		
	}

}
