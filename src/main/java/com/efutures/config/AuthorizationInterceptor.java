package com.efutures.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorizationInterceptor implements RequestInterceptor {
    private final ClientConfigProvider configProvider;

    public AuthorizationInterceptor(ClientConfigProvider configProvider) {
        this.configProvider = configProvider;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Content-Type", configProvider.getContentType());
        requestTemplate.header("Authorization","Bearer "+configProvider.getApiKey());
        String requestString = requestTemplate.request().toString();
        log.info("request {}",requestString);
    }

}
