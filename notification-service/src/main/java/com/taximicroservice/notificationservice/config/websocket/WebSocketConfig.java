package com.taximicroservice.notificationservice.config.websocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value(value = "${notificationService.registerStompEndpoint}")
    private String registerStompEndpoint;

    @Value(value = "${notificationService.allowedOrigins}")
    private String[] allowedOrigins;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic/", "/queue/");
        registry.setApplicationDestinationPrefixes("/app");;
        registry.setUserDestinationPrefix("/user");

        /*registry.setApplicationDestinationPrefixes("/app");
        registry.enableStompBrokerRelay("/topic/", "/queue/")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");*/
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(registerStompEndpoint)
                .setAllowedOrigins(allowedOrigins)
                .withSockJS();
    }

}
