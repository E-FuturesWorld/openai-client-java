package com.efutures;

import com.efutures.client.OpenAIClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class OpenAIService {

    private final OpenAIClient client;

    private final ExecutorService executorService;

    public OpenAIService(OpenAIClient client, ExecutorService executorService) {
        this.client = client;
        this.executorService = executorService;
    }

    public CompletableFuture<ResponseEntity<String>> getModels(){
        CompletableFuture<ResponseEntity<String>> responseEntityCompletableFuture = CompletableFuture.supplyAsync(() -> client.listModels(), executorService)
                .whenCompleteAsync((stringResponseEntity, throwable) -> {
                }, executorService);
        return responseEntityCompletableFuture;
    }
}
