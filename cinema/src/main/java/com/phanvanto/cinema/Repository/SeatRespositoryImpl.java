package com.phanvanto.cinema.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.Seat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public class SeatRespositoryImpl implements SeatRespository{

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Seat> getList() {
		String hql = "From Seat";
		return entityManager.createQuery(hql, Seat.class).getResultList();
	}

	@Override
	public void AddorUpdate(Seat seat) {
		entityManager.persist(seat);
		
	}
	

}
