package com.phanvanto.cinema.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
		 registry.addEndpoint("/ws") // Kết nối WebSocket tại endpoint /ws
         .setAllowedOriginPatterns("*") // Cho phép tất cả domain (hoặc giới hạn theo domain production)
         .withSockJS(); // Hỗ trợ SockJS fallback
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // Prefix cho topic gửi dữ liệu
        registry.setApplicationDestinationPrefixes("/app"); // Prefix cho dữ liệu gửi từ client
    }
}
