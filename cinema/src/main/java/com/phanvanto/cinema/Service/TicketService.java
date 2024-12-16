package com.phanvanto.cinema.Service;

import java.util.List;

import com.phanvanto.cinema.Entity.Ticket;

public interface TicketService {
	List<Ticket> getList();
	void AddorUpdate(Ticket ticket);
	void deleteById(Long id);
	Ticket getTicketById(Long id);
	List<Ticket> getAllTicketByUserId(Long id);
}
