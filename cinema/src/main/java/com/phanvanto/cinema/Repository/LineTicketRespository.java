package com.phanvanto.cinema.Repository;



import java.util.List;

import com.phanvanto.cinema.Entity.Line_Ticket;

public interface LineTicketRespository {

	List<Line_Ticket> getList();
	void AddorUpdate(Line_Ticket line_ticket);
    void deleteById(Long id);
    Line_Ticket getLineTicketByLine_id(Long id);
}
