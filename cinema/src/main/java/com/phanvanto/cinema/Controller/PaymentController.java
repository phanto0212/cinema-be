package com.phanvanto.cinema.Controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phanvanto.cinema.DTO.PaymentDTO;
import com.phanvanto.cinema.DTO.ResponseObject;
import com.phanvanto.cinema.Service.PaymentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("${spring.application.api-prefix}/payment")
public class PaymentController {
    private final PaymentService paymentService;
    

    
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
        String reqUserid = request.getParameter("userId");
        String reqBookingid = request.getParameter("bookingId");
        String reqAmount = request.getParameter("vnp_Amount");
  
        if (status != null) {
            if ("00".equals(status)) {
            	try {
            		
                	
            	}
            	catch(Exception e) {
            		e.printStackTrace();
            		throw e;
            	}
            	
            	
            	
                return new ResponseObject<>(HttpStatus.OK, "Success", new PaymentDTO.VNPayResponse("00", "Success", ""));
            } else {
            	try {
            		
                	
            	}
            	catch(Exception e) {
            		e.printStackTrace();
            		throw e;
            	}
                return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", null);
            }
        } else {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Response code is missing", null);
        }
    }
}

