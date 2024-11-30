package com.phanvanto.cinema.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.LineCombo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public class LineComboRespositoryImpl implements LineComboRespository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void AddorUpdate(LineCombo line_combo) {
		if(line_combo.getId() == null) {
			entityManager.persist(line_combo);
		}else {
			entityManager.merge(line_combo);
		}
		
	}

	@Override
	public List<LineCombo> getListByticketId(Long id) {
		String hql = "From LineCombo l where l.ticket_id = :id";
		TypedQuery<LineCombo> query = entityManager.createQuery(hql, LineCombo.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

}
