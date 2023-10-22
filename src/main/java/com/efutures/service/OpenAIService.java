package com.efutures.service;

import com.efutures.client.OpenAIClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public  String listModels(){
        String execute = execute(client.listModels());
        return execute;


    }

    public static <T> T execute(ResponseEntity<T> apiCall){

//        try{
//            return apiCall.getBody();
//        } catch (FeignException.FeignClientException e){
//        }
        try {
            return apiCall.getBody();
        }catch (FeignException.FeignClientException e){
            log.info("Error {}",e.responseBody().get());
        }

        return null;
    }


}
