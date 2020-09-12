package com.taximicroservice.bookingservice;

import org.locationtech.jts.geom.GeometryFactory;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class BookingServiceApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public GeometryFactory geometryFactory() {
        return new GeometryFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(BookingServiceApplication.class, args);
    }

}
