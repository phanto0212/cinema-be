package com.phanvanto.cinema.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.Combo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public class ComboRespositoryImpl implements ComboRespository{
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Combo> getList() {
		String hql="From Combo";
		return entityManager.createQuery(hql, Combo.class).getResultList();
	}

	@Override
	public Combo getCombobyId(Long id) {
		return entityManager.find(Combo.class, id);
	}

	@Override
	public void AddorUpdate(Combo combo) {
		if(combo.getId()==null) {
			entityManager.persist(combo);
		}
		else {
			entityManager.merge(combo);
		}
		
	}

	@Override
	public void deleteComboById(Long id) {
		try {
			Combo combo = entityManager.find(Combo.class, id);
			if(combo!=null) {
				entityManager.remove(combo);
			}
			else {
				System.out.print("khong the xoa combo khong ton tai");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
