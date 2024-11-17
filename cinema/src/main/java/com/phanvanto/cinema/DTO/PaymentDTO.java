package com.phanvanto.cinema.DTO;

public class PaymentDTO {
    public static class VNPayResponse {
        private String code;
        private String message;
        private String paymentUrl;

        public VNPayResponse(String code, String message, String paymentUrl) {
            this.code = code;
            this.message = message;
            this.paymentUrl = paymentUrl;
        }

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getPaymentUrl() {
			return paymentUrl;
		}

		public void setPaymentUrl(String paymentUrl) {
			this.paymentUrl = paymentUrl;
		}

        // Getters và Setters
        
    }
}
