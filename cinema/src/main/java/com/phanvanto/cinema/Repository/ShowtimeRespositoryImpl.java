package com.phanvanto.cinema.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.Showtime;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ShowtimeRespositoryImpl implements ShowtimeRespository{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Showtime> getList() {
		String hql = "From Showtime";
		return entityManager.createQuery(hql, Showtime.class).getResultList();
	}

	@Override
	public void AddorUpdate(Showtime showtime) {
		if(showtime.getId()==null) {
			entityManager.persist(showtime);
		}
		else {
			entityManager.merge(showtime);
		}
		
	}

	@Override
	public void DeleteById(Long showtime_id) {
		try {
			Showtime showtime = entityManager.find(Showtime.class, showtime_id);
			if(showtime == null) {
				System.out.print("khong ton tai showtime voi id la "+ showtime_id);
			}
			else {
				entityManager.remove(showtime);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Showtime getShowTimebyShowtime_Id(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Showtime.class, id);
	}

	@Override
	public List<Showtime> getListShowtimebyCinema_idAndMovieId(Long cinema_id, Long movie_id,Date day_book) {
		String hql = "From Showtime s where s.cinema_id =: cinema_id and s.movie_id =: movie_id and s.day_show =: day_book ";
		TypedQuery<Showtime> query = entityManager.createQuery(hql, Showtime.class);
		query.setParameter("cinema_id", cinema_id);
		query.setParameter("movie_id", movie_id);
		query.setParameter("day_book", day_book);
		return query.getResultList();
	}

	

}
