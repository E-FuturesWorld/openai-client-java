package com.efutures.service;

import com.efutures.client.OpenAIClient;
import com.efutures.dto.completion.CreateCompletion;
import com.efutures.dto.model.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
@Slf4j
public class OpenAIService {

    private final OpenAIClient client;
    private final ExecutorService executorService;

    public OpenAIService(OpenAIClient client, ExecutorService executorService) {
        this.client = client;
        this.executorService = executorService;

    }

    public List<Model> listModels(){
        return execute(client.listModels()).data;
    }
     public Model getModel(String modelId){
        return execute(client.getModel(modelId));
     }

     public String createCompletion(CreateCompletion createCompletion){
        return execute(client.createCompletion(createCompletion));
     }

    private <T> T execute(ResponseEntity<T> apiCall){
        return CompletableFuture.supplyAsync(() -> {
            return apiCall.getBody();
        },executorService).join();

    }

}
