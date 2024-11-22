package com.phanvanto.cinema.Repository;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.DTO.CinemaDTO;
import com.phanvanto.cinema.Entity.Cinema;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CinemaRespositoryImpl implements CinemaRespository {

	@PersistenceContext
    private EntityManager entityManager;
	@Override
	public List<Cinema> getList() {
		try {
			String hql = "From Cinema";
			return entityManager.createQuery(hql, Cinema.class).getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void AddorUpdate(Cinema cinema) {
		try {
			if(cinema.getId() == null) {
				entityManager.persist(cinema);
			}
			else {
				entityManager.merge(cinema);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	@Override
	public Cinema getCinemaById(Long id) {
		try {
			return entityManager.find(Cinema.class, id);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public int deleteById(Long id) {
		Cinema cinema = entityManager.find(Cinema.class, id);
		if(cinema == null ) {
			System.out.print("khong ton tai cinema với id này");
			return 0;
		}
		else {
			entityManager.remove(cinema);
			return 1;
		}
		
	}

	@Override
	public List<Cinema> getAllCinemaByCity(String nameCity) {
		try {
			String hql = "From Cinema c Where c.city =: nameCity";
			TypedQuery<Cinema> query = entityManager.createQuery(hql, Cinema.class);
			query.setParameter("nameCity", nameCity);
			return query.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<CinemaDTO> getCinemasByMovieAndCity(Long movieId, Date dayShow, String city) {
	    String queryStr = "SELECT s.cinema_id, c.name AS cinema_name, c.address , s.movie_id, s.time_show, s.day_show " +
	                      "FROM Showtime s JOIN Cinema c ON s.cinema_id = c.id " +
	                      "WHERE s.movie_id = :movie_id " +
	                      "AND s.day_show = :day_show " +
	                      "AND c.city = :city";

	    // Sử dụng TypedQuery với DTO
	    TypedQuery<Object[]> query = entityManager.createQuery(queryStr, Object[].class);
	    
	    query.setParameter("movie_id", movieId);
	    query.setParameter("day_show", dayShow);
	    query.setParameter("city", city);

	    List<Object[]> results = query.getResultList();
	    List<CinemaDTO> cinemaDTOs = new ArrayList<>();

	    // Chuyển đổi Object[] thành CinemaDTO
	    for (Object[] result : results) {
	        CinemaDTO dto = new CinemaDTO();
	        dto.setCinemaId((Long) result[0]);
	        dto.setCinemaName((String) result[1]);
	        dto.setAddress((String) result[2]);
	        dto.setMovieId((Long) result[3]);
	        dto.setTimeShow((LocalTime) result[4]);
	        dto.setDayShow((Date) result[5]);
	        cinemaDTOs.add(dto);
	    }

	    return cinemaDTOs;
	}

	

}
