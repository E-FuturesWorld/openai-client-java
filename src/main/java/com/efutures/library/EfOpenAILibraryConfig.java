package com.efutures.library;

import com.efutures.client.OpenAIClient;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@EnableFeignClients(clients = OpenAIClient.class)
@ComponentScan(basePackages = {"com.efutures.*"})
@Configuration
@PropertySource(value = "classpath:ef-open-ai-client-properties.yml",factory = YamlPropertySourceFactory.class)
public class EfOpenAILibraryConfig {
}
