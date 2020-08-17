package com.taximicroservice.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class SwaggerSpecConfig {

    @Value("${swagger.config.root-dir}")
    private String swaggerConfigRootDirectory;

    @Value("${swagger.config.swagger-file-name}")
    private String swaggerFileName;

    @Autowired
    private ResourceLoader resourceLoader;


    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider) {
        return () -> {
            try {
                return getListOfApiDirs()
                        .stream()
                        .map(this::mapApiDirectoryToResourceLocation)
                        .collect(Collectors.toList());
            } catch (Exception exception) {
                return new ArrayList<>();
            }
        };
    }

    private List<String> getListOfApiDirs() {
        String[] swaggerApisArray = {
                "booking-service-api",
                "chat-service-api",
                "driver-service-api",
                "notification-service-api",
                "opinion-service-api",
                "passenger-service-api",
                "payments-service-api",
                "sales-service-api",
                "user-service-api"
        };
        return new ArrayList<>(Arrays.asList(swaggerApisArray));
    }

    private SwaggerResource mapApiDirectoryToResourceLocation(String apiDirectory) {
        String resourcePath = "/" + String.join("/", swaggerConfigRootDirectory, apiDirectory, swaggerFileName);
        return loadResource(resourcePath, apiDirectory);
    }

    private SwaggerResource loadResource(String resourcePath, String apiDirectory) {
        SwaggerResource wsResource = new SwaggerResource();
        wsResource.setName(apiDirectory);
        wsResource.setSwaggerVersion("2.0");
        wsResource.setLocation(resourcePath);
        return wsResource;
    }

}