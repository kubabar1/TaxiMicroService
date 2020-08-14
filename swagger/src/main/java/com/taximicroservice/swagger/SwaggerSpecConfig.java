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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.*;
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
    public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider) throws IOException {
       return () -> {
            try {
                return getListOfApiDirs()
                        .stream()
                        .map(this::mapApiDirectoryToResourceLocation)
                        .collect(Collectors.toList());
            } catch (IOException exception) {
                return new ArrayList<>();
            }
        };
    }

    private List<String> getListOfApiDirs() throws IOException {
        String classpathSwaggerConfigPath = "classpath:static/" + swaggerConfigRootDirectory;
        URI uriToRootConfigDit = resourceLoader.getResource(classpathSwaggerConfigPath).getURI();
        File[] listOfDirectoriesInSwaggerConfig = new File(uriToRootConfigDit).listFiles(File::isDirectory);
        if (Objects.isNull(listOfDirectoriesInSwaggerConfig)) {
            return new ArrayList<>();
        } else {
            return Arrays.stream(listOfDirectoriesInSwaggerConfig).map(File::getName).collect(Collectors.toList());
        }
    }

    private SwaggerResource mapApiDirectoryToResourceLocation(String apiDirectory) {
        String resourcePath = "/" +  String.join("/", swaggerConfigRootDirectory, apiDirectory, swaggerFileName);
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