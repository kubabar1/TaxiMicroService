package com.taximicroservice.notificationservice.config.websocket.security;

import com.taximicroservice.notificationservice.config.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


@Component
public class AuthChannelInterceptorAdapter implements ChannelInterceptor {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private WebSocketAuthenticatorService webSocketAuthenticatorService;


    @Override
    public Message<?> preSend(final Message<?> message, final MessageChannel channel) throws AuthenticationException {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if (Objects.isNull(accessor)) {
            throw new InternalAuthenticationServiceException("Accessor is null");
        }

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String bearerToken = accessor.getFirstNativeHeader("X-Authorization");
            jwtTokenUtil.validateToken(bearerToken);

            String jwtFromToken = bearerToken.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(jwtFromToken);
            List<String> userRoles = jwtTokenUtil.getUserRoles(jwtFromToken);

            accessor.setUser(webSocketAuthenticatorService.getAuthenticatedOrFail(username, userRoles));
        }
        return message;
    }

}