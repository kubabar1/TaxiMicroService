package com.taximicroservice.notificationservice.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(1)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value(value = "${notificationService.registerStompEndpoint}")
    private String registerStompEndpoint;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers(registerStompEndpoint + "/**").permitAll()
                .anyRequest().denyAll();
    }

}
