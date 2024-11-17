package com.phanvanto.cinema.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.Comments;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public class CommentRespositoryImpl implements CommentRespository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Comments> getListByMovieId(Long movie_id) {
		try {
			String hql = "From Comments c WHERE c.movie_id = :movie_id";
			TypedQuery<Comments> query = entityManager.createQuery(hql, Comments.class);
			query.setParameter("movie_id", movie_id);
			return query.getResultList();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void AddorUpdate(Comments comment) {
		if(comment.getId()==null) {
			entityManager.persist(comment);
		}
		else {
			entityManager.merge(comment);
		}
		
	}

	@Override
	public int deleteCommentById(Integer id) {
		try {
			Comments comment = entityManager.find(Comments.class, id);
			if(comment==null) {
				return 0;
			}
			else {
				entityManager.remove(comment);
				return 1;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
			
		}
	}

}
