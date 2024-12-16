package com.phanvanto.cinema.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.Cinema;
import com.phanvanto.cinema.Entity.Movie;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public class MovieRespositoryImpl implements MovieRespository {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Movie> getlist() {
		try {
			String hql = "From Movie";
			return entityManager.createQuery(hql, Movie.class).getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void AddorUpdate(Movie movie) {
		try {
			if(movie.getId() == null) {
				entityManager.persist(movie);
			}
			else {
				entityManager.merge(movie);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public int deleteMovie(Long id) {
		Movie movie = entityManager.find(Movie.class, id);
		if(movie == null ) {
			System.out.print("khong ton tai Movie với id này");
			return 0;
		}
		else {
			entityManager.remove(movie);
			return 1;
		}
	}

	@Override
	public Movie getMovieById(Long id) {
		Movie movie = entityManager.find(Movie.class, id);
		if(movie!=null) {
			return movie;
		}
		else {
		return null;
	}
	}

	@Override
	public List<Movie> getAllMovieShowNow() {
		String hql = "FROM Movie m WHERE m.release_date <= CURRENT_DATE ";
		return entityManager.createQuery(hql, Movie.class).getResultList();
	}

	@Override
	public List<Movie> getAllMovieComingSoon() {
		String hql = "FROM Movie m WHERE m.release_date > CURRENT_DATE ";
		return entityManager.createQuery(hql, Movie.class).getResultList();
	}

	@Override
	public List<Movie> getMovieByKey(String key) {
		try {
			String hql = "SELECT m FROM Movie m WHERE " +
			           "LOWER(m.title) LIKE LOWER(CONCAT('%', :key, '%')) OR " +
			           "LOWER(m.director) LIKE LOWER(CONCAT('%', :key, '%')) " ;
			TypedQuery<Movie> query = entityManager.createQuery(hql, Movie.class);
			query.setParameter("key", key);
			return query.getResultList();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
