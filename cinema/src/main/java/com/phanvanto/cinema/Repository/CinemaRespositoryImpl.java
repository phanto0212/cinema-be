package com.phanvanto.cinema.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.Cinema;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

	

}
