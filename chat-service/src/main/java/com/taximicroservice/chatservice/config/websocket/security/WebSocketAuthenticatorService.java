package com.taximicroservice.chatservice.config.websocket.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import java.util.List;
import java.util.Objects;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Configuration
public class WebSocketAuthenticatorService {

    public UsernamePasswordAuthenticationToken getAuthenticatedOrFail(String username, List<String> userRole)
            throws AuthenticationException {

        if (Objects.isNull(username) || username.trim().isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Username was null or empty.");
        }

        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                createAuthorityList(userRole.toArray(String[]::new))
        );
    }

}
