package com.phanvanto.cinema.Controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phanvanto.cinema.DTO.PaymentDTO;
import com.phanvanto.cinema.DTO.ResponseObject;
import com.phanvanto.cinema.Entity.Ticket;
import com.phanvanto.cinema.Service.PaymentService;
import com.phanvanto.cinema.Service.TicketService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${spring.application.api-prefix}/payment")
public class PaymentController {
    private final PaymentService paymentService;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @Autowired
    private TicketService ticketService;
    
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    
    @GetMapping("/vn-pay")
    public ResponseObject<PaymentDTO.VNPayResponse> pay(HttpServletRequest request) {
        return new ResponseObject<>(HttpStatus.OK, "Success", paymentService.createVnPayPayment(request));
    }

   
    @GetMapping("/vn-pay-callback")
    public ResponseObject<PaymentDTO.VNPayResponse> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        String reqUserId = request.getParameter("userId");
        String reqBookingId = request.getParameter("bookingId");

        if (status != null) {
            try {
                Long ticketId = Long.parseLong(reqBookingId);
                Ticket ticket = ticketService.getTicketById(ticketId);
                if ("00".equals(status)) { // Thanh toán thành công
                    ticket.setStatus("paid");
                    Long movieId = ticket.getMovie_id();
                    ticketService.AddorUpdate(ticket);
                    System.out.print(movieId);
                    messagingTemplate.convertAndSend(
                            "/topic/movie/" + movieId, 
                            "Payment successful for movie " + movieId
                        );

                    // Gửi thông báo qua WebSocket
                    messagingTemplate.convertAndSend(
                        "/topic/payment/" + ticketId, 
                        "Payment successful for ticket " + ticketId
                    );
                    
                    

                    return new ResponseObject<>(
                        HttpStatus.OK,
                        "Success",
                        new PaymentDTO.VNPayResponse("00", "Success", "")
                    );
                } else { // Thanh toán thất bại
                    ticket.setStatus("cancelled");
                    ticketService.AddorUpdate(ticket);

                    // Gửi thông báo thất bại qua WebSocket
                    messagingTemplate.convertAndSend(
                        "/topic/payment/" + ticketId, 
                        "Payment failed for ticket " + ticketId
                    );

                    return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error processing payment callback");
            }
        } else {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Response code is missing", null);
        }
    }
}

