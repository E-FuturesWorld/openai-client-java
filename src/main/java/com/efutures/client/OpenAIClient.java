package com.efutures.client;

import com.efutures.config.AuthorizationInterceptor;
import com.efutures.dto.completion.CreateCompletion;
import com.efutures.dto.model.Model;
import com.efutures.dto.wrapper.ListResponseWrapper;
import com.efutures.dto.wrapper.SingleResponseWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "open-ai-api", url = "https://api.openai.com/",configuration = AuthorizationInterceptor.class,dismiss404 = true)
public interface OpenAIClient {

    @GetMapping("v1/models")
    ResponseEntity<ListResponseWrapper<Model>> listModels();
    @GetMapping("v1/models/{model_id}")
    ResponseEntity<Model> getModel(@PathVariable("model_id")String modelId);
    @PostMapping("v1/completions")
    ResponseEntity<String> createCompletion(@RequestBody CreateCompletion createCompletion);


}
