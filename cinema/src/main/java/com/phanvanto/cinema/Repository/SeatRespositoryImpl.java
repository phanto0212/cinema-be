package com.phanvanto.cinema.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.phanvanto.cinema.Entity.Seat;
import com.phanvanto.cinema.Filter.SeatDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public class SeatRespositoryImpl implements SeatRespository{

	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Seat> getList() {
		String hql = "From Seat";
		return entityManager.createQuery(hql, Seat.class).getResultList();
	}

	@Override
	public void AddorUpdate(Seat seat) {
		entityManager.persist(seat);
		
	}

	@Override
	public void deleteSeatById(Long id) {
		Seat seat = entityManager.find(Seat.class, id);
		entityManager.remove(seat);
		
	}

	@Override
	public List<SeatDTO> getAllSeatByShowtimeId(Long showtimeId) {
		String hql = """
			    SELECT DISTINCT 
			        seat.id AS seat_id,
	                seat.screen_id,
	                seat.seat_number,
	                seat.seat_type,
			        CASE 
			            WHEN MAX(ticket.status) = 'paid' THEN 'booked'
			            WHEN MAX(ticket.status) = 'confirmed' THEN 'available'
			            ELSE 'available'
			        END AS status
			    FROM Seat seat
			    LEFT JOIN Line_Ticket lineTicket ON seat.id = lineTicket.seat_id
			    LEFT JOIN Ticket ticket ON lineTicket.ticket_id = ticket.ticket_id
			        AND ticket.showtime_id = :showtimeId
			    WHERE seat.screen_id = 
			        (SELECT s.screen_id FROM Showtime s WHERE s.id = :showtimeId)
			    GROUP BY 
			        seat.id, seat.screen_id, seat.seat_number, seat.seat_type
			"""
;

	    Query query = entityManager.createQuery(hql);
	    query.setParameter("showtimeId", showtimeId);

	    List<Object[]> resultList = query.getResultList();
	    if (resultList.isEmpty()) {
	        String fallbackHql = """
	            SELECT 
	                seat.id AS seat_id,
	                seat.screen_id,
	                seat.seat_number,
	                seat.seat_type,
	                'available' AS status
	            FROM Seat seat
	            WHERE seat.screen_id = 
	                (SELECT s.screen_id FROM Showtime s WHERE s.id = :showtimeId)
	        """;
	        query = entityManager.createQuery(fallbackHql);
	        query.setParameter("showtimeId", showtimeId);
	        resultList = query.getResultList();
	    }
	    List<SeatDTO> seatDTOList = new ArrayList<>();
	    for (Object[] row : resultList) {
	        // Kiểm tra kiểu dữ liệu của mỗi phần tử trước khi ép kiểu
	        Integer seatId = (Integer) row[0];  // seat_id
	        Integer screenId = (Integer) row[1];  // screen_id
	        String seatNumber = (String) row[2];  // seat_number
	        String seatType = (String) row[3];  // seat_type
	        String status = (String) row[4];  // status

	        SeatDTO seatDTO = new SeatDTO(seatId, screenId, seatNumber, seatType, status);
	        seatDTOList.add(seatDTO);
	    }
	    return seatDTOList;
	}

	@Override
	public List<Seat> getListByScreenId(Integer id) {
		String hql = "From Seat s Where s.screen_id = : id";
		TypedQuery<Seat> query = entityManager.createQuery(hql, Seat.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

}

