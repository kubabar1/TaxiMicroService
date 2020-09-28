package com.taximicroservice.notificationservice.config.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    @Value("${notificationService.tokenValiditySeconds}")
    public long jwtTokenValidity;

    @Value("${notificationService.jwt.secret}")
    private String secret;


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public List<String> getUserRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", ArrayList.class);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(String userName, List<String> userRoles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userRoles);
        return doGenerateToken(claims, userName);
    }

    public void validateToken(String token) throws InternalAuthenticationServiceException {
        if (Objects.isNull(token)) {
            throw new InternalAuthenticationServiceException("JWT token is null");
        } else if (token.length() < 8 && !token.startsWith("Bearer ")) {
            throw new InternalAuthenticationServiceException("Invalid JWT token");
        }
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody();
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenValidity * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

}
