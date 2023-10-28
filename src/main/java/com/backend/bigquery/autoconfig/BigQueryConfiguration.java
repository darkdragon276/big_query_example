package com.backend.bigquery.autoconfig;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.common.collect.ImmutableSet;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@Configuration
@EnableAutoConfiguration
public class BigQueryConfiguration {
    @Bean
    public static BigQuery bigQuery() throws IOException {
        GoogleCredentials credentials = ServiceAccountCredentials.getApplicationDefault()
                        .createScoped(
                                ImmutableSet.of(
                                        "https://www.googleapis.com/auth/bigquery",
                                        "https://www.googleapis.com/auth/drive"));
        return BigQueryOptions.newBuilder().setProjectId("bigquery-402309").setCredentials(credentials).build().getService();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }
}
