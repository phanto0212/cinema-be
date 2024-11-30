package com.phanvanto.cinema.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phanvanto.cinema.Entity.Line_Ticket;
import com.phanvanto.cinema.Repository.LineTicketRespository;

@Service
public class LineTicketServiceImpl implements LineTicketService {

	@Autowired
	private LineTicketRespository lineTicketRespository;
	@Override
	public List<Line_Ticket> getList() {
		return lineTicketRespository.getList();
	}

	@Override
	public void AddorUpdate(Line_Ticket line_ticket) {
		lineTicketRespository.AddorUpdate(line_ticket);
		
	}

	@Override
	public void deleteById(Long id) {
		lineTicketRespository.deleteById(id);
		
	}

	@Override
	public Line_Ticket getLineTicketByLine_id(Long id) {
		return lineTicketRespository.getLineTicketByLine_id(id);
	}

	@Override
	public List<Line_Ticket> getListByTicketId(Long id) {
		// TODO Auto-generated method stub
		return lineTicketRespository.getListByTicketId(id);
	}

}
