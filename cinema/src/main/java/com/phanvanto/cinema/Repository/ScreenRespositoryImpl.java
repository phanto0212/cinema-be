package com.phanvanto.cinema.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.Screen;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ScreenRespositoryImpl implements ScreenRespository{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Screen> getList() {
		return entityManager.createQuery("From Screen", Screen.class).getResultList();
	}

	@Override
	public void AddorUpdate(Screen screen) {
		try {
			if(screen.getId() == null) {
				entityManager.persist(screen);
			}
			else {
				entityManager.merge(screen);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public Screen getScreenById(Long id) {
		Screen screen = entityManager.find(Screen.class, id);
		if(screen == null) {
			return null;
		}
		else {
			return screen;
		}
		
	}

	@Override
	public void deleteById(Long id) {
		try {
			entityManager.remove(entityManager.find(Screen.class, id));
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
