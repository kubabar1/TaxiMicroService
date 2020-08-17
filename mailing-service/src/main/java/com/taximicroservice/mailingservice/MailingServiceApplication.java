package com.taximicroservice.mailingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MailingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailingServiceApplication.class, args);
    }

}
