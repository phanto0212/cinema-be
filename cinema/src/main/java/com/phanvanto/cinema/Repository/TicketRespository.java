package com.phanvanto.cinema.Repository;

import java.util.List;

import com.phanvanto.cinema.Entity.Ticket;

public interface TicketRespository {

	List<Ticket> getList();
	void AddorUpdate(Ticket ticket);
	void deleteById(Long id);
	Ticket getTicketById(Long id);
	List<Ticket> getAllTicketByUserId(Long id);
}
