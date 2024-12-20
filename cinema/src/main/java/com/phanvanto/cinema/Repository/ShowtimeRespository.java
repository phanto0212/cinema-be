package com.phanvanto.cinema.Repository;

import java.sql.Date;
import java.util.List;

import com.phanvanto.cinema.Entity.Showtime;

public interface ShowtimeRespository {

	List<Showtime> getList();
	Showtime getShowTimebyShowtime_Id(Long id);
	List<Showtime> getListShowtimebyCinema_idAndMovieId(Long cinema_id, Long movie_id,Date day_book);
	void AddorUpdate(Showtime showtime);
	void DeleteById(Long showtime_id);
	
}
