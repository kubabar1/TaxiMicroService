package com.taximicroservice.chatservice.config.websocket.security;

import com.taximicroservice.chatservice.config.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

import java.util.List;
import java.util.Objects;

public class AuthChannelInterceptorAdapter implements ChannelInterceptor {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private WebSocketAuthenticatorService webSocketAuthenticatorService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if(Objects.isNull(accessor)) {
            throw new InternalAuthenticationServiceException("Accessor is null");
        }

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String bearerToken = accessor.getFirstNativeHeader("X-Authorization");
            jwtTokenUtil.validateToken(bearerToken);

            String jwtFromToken = jwtTokenUtil.getJwtFromBearerToken(bearerToken);
            String username = jwtTokenUtil.getUsernameFromToken(jwtFromToken);
            List<String> userRoles = jwtTokenUtil.getUserRoles(jwtFromToken);

            accessor.setUser(webSocketAuthenticatorService.getAuthenticatedOrFail(username, userRoles));
        }
        return message;
    }

}
