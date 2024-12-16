package com.phanvanto.cinema.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.Ratings;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public class RatingRespositoryImpl implements RatingRespository {

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Ratings> getAllratingByMovieId(Long movie_id) {
		try {
			String hql = "From Ratings r Where r.movie_id =: movie_id";
			TypedQuery<Ratings> query = entityManager.createQuery(hql, Ratings.class);
			query.setParameter("movie_id", movie_id);
			return query.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteRatingById(Integer id) {
        try {
        	Ratings rating = entityManager.find(Ratings.class, id);
        	if(rating.getRating_id()==null) {
        		System.out.print("khong co rating ma xoa nhe");
        	}
        	else {
        		entityManager.remove(rating);
        	}
        }
        catch(Exception e) {
        	e.printStackTrace();
        	throw e;
        }
		
	}

	@Override
	public void AddorUpdate(Ratings rating) {
		try {
			if(rating.getRating_id()==null) {
				entityManager.persist(rating);
			}else {
				entityManager.merge(rating);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
