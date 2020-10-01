package com.taximicroservice.chatservice.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value(value = "${chatService.registerStompEndpoint}")
    private String registerStompEndpoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers(registerStompEndpoint + "/**").permitAll()
                .anyRequest().permitAll(); // TODO: Fix security
        http.csrf().disable(); // TODO: Fix security
    }

}
