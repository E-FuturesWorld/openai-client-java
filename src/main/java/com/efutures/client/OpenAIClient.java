package com.efutures.client;

import com.efutures.config.AuthorizationInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "open-ai-api", url = "https://api.openai.com/",configuration = AuthorizationInterceptor.class,dismiss404 = true)
public interface OpenAIClient {

    @GetMapping("v1/models")
    ResponseEntity<String> listModels();


}
