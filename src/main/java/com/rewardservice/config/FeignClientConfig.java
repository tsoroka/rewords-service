package com.rewardservice.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Common Feign client configuration for all Feign clients in this project.
 * Provides a Retryer bean to configure retry behavior.
 */
@Configuration
public class FeignClientConfig {

    @Bean
    public ErrorDecoder feignErrorDecoder() {
        return new CustomErrorDecoder();
    }

    private static class CustomErrorDecoder implements ErrorDecoder {

        @Override
        public Exception decode(String methodKey, Response response) {
            return switch (response.status()) {
                case 400 -> new IllegalArgumentException("Bad Request");
                case 404 -> new RuntimeException("Resource Not Found");
                default -> new Exception("Generic error: " + response.status());
            };
        }
    }


}

