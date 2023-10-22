package com.efutures.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties("open-ai.client.configuration")
@Configuration
@Data

public  class OpenAIClientConfig extends ClientConfigProvider {

    private String apiKey;
    private String contentType;
    private String model;
    private String temperature;
    private String tokens;
    private String maxToken;

    @Override
    String getApiKey() {
        return apiKey;
    }

    @Override
    String getContentType() {
        return contentType;
    }

    @Override
    String getModel() {
        return model;
    }

    @Override
    String getTemperature() {
        return temperature;
    }

    @Override
    String getTokens() {
        return tokens;
    }

    @Override
    String getMaxTokens() {
        return tokens;
    }
}
