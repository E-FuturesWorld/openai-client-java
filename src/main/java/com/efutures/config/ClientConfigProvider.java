package com.efutures.config;

public abstract class ClientConfigProvider {
    abstract String getApiKey();
    abstract String getContentType();
    abstract String getModel();
    abstract String getTemperature();
    abstract String getTokens();
    abstract String getMaxTokens();
}
