package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.Showtime;
import com.phanvanto.cinema.Repository.ShowtimeRespository;
@Service
public class ShowtimeServiceImpl implements ShowtimeService {

	@Autowired 
	private ShowtimeRespository showtimeRespository;
	@Override
	public List<Showtime> getList() {
		return showtimeRespository.getList();
	}

	@Override
	public Showtime getShowTimebyShowtime_Id(Long id) {
		return showtimeRespository.getShowTimebyShowtime_Id(id);
	}

	@Override
	public List<Showtime> getListShowtimebyCinema_idAndMovieId(Long cinema_id, Long movie_id) {
		return showtimeRespository.getListShowtimebyCinema_idAndMovieId(cinema_id, movie_id);
	}

	@Override
	public void AddorUpdate(Showtime showtime) {
		showtimeRespository.AddorUpdate(showtime);
	}

	@Override
	public void DeleteById(Long showtime_id) {
		showtimeRespository.DeleteById(showtime_id);
	}

}
