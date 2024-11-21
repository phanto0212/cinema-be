package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.Ticket;
import com.phanvanto.cinema.Repository.TicketRespository;
@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRespository ticketRespository;
	
	@Override
	public List<Ticket> getList() {
		return ticketRespository.getList();
	}

	@Override
	public void AddorUpdate(Ticket ticket) {
		ticketRespository.AddorUpdate(ticket);
		
	}

	@Override
	public void deleteById(Long id) {
		ticketRespository.deleteById(id);
		
	}

	@Override
	public Ticket getTicketById(Long id) {
		return ticketRespository.getTicketById(id);
	}

}
