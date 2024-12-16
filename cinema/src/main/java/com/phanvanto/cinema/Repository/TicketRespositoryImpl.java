package com.phanvanto.cinema.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.Ticket;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class TicketRespositoryImpl implements TicketRespository {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Ticket> getList() {
		String hql = "From Ticket";
		return entityManager.createQuery(hql, Ticket.class).getResultList();
	}

	@Override
	public void AddorUpdate(Ticket ticket) {
		if(ticket.getTicket_id()==null) {
			entityManager.persist(ticket);
		}
		else {
			entityManager.merge(ticket);
		}
		
	}

	@Override
	public void deleteById(Long id) {
		Ticket ticket =entityManager.find(Ticket.class, id);
		if(ticket!=null) {
			entityManager.remove(ticket);
		}
		else {
			System.out.print("ticket loi repo");
		}
	}

	@Override
	public Ticket getTicketById(Long id) {
		
		return entityManager.find(Ticket.class, id);
	}

	@Override
	public List<Ticket> getAllTicketByUserId(Long id) {
		String hql = "From Ticket t WHERE t.user_id =: id and t.status =: status";
		TypedQuery<Ticket> query =  entityManager.createQuery(hql, Ticket.class);
		query.setParameter("id", id);
		query.setParameter("status", "paid");
		
		return query.getResultList();
	}

}
