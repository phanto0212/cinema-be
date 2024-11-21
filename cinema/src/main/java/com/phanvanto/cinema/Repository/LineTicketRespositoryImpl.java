package com.phanvanto.cinema.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.Line_Ticket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
@Repository
@Transactional

public class LineTicketRespositoryImpl implements LineTicketRespository {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Line_Ticket> getList() {
		String hql = "From Line_Ticket";
		return entityManager.createQuery(hql, Line_Ticket.class).getResultList();
	}

	@Override
	public void AddorUpdate(Line_Ticket line_ticket) {
		if(line_ticket.getId()==null) {
			entityManager.persist(line_ticket);
		}
		else {
			entityManager.merge(line_ticket);
		}
		
	}

	@Override
	public void deleteById(Long id) {
		try {
			Line_Ticket line_ticket =entityManager.find(Line_Ticket.class, id);
			if(line_ticket!=null) {
				entityManager.remove(line_ticket);
			}
			else {
				System.out.print("loi ow lineticket");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public Line_Ticket getLineTicketByLine_id(Long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Line_Ticket.class, id);
	}

}
