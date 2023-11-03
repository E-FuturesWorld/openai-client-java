package com.efutures.service;

import com.efutures.client.OpenAIClient;
import com.efutures.dto.request.completion.CreateCompletion;
import com.efutures.dto.response.CompletionObject;
import com.efutures.dto.response.model.Model;
import com.efutures.dto.response.ChatCompletion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper mapper;

    public OpenAIService(OpenAIClient client, ExecutorService executorService, ObjectMapper mapper) {
        this.client = client;
        this.executorService = executorService;
        this.mapper = mapper;
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

     public <T> T extractFromCompletion(CreateCompletion createCompletion, Class<T> targetType, int choiceIndex){
         T extract = extract(client.extractChoices(createCompletion), targetType, 0);
         return  extract;
     }

    public <T> T extractFromCompletion(CreateCompletion createCompletion, TypeReference<T> targetType, int choiceIndex){
        T extract = extract(client.extractChoices(createCompletion), targetType, 0);
        return  extract;
    }

    private <T> T execute(ResponseEntity<T> apiCall){
        return CompletableFuture.supplyAsync(() -> {
            return apiCall.getBody();
        },executorService).join();


    }

    private <T> T extract(ResponseEntity<CompletionObject> apiCall, Class<T> targetType, int choiceIndex) {
        String text = apiCall.getBody().getChoices().get(choiceIndex).getText();
        log.info("text {}",text);
        try {
            return mapper.readValue(text,targetType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T extract(ResponseEntity<CompletionObject> apiCall, TypeReference<T> targetType, int choiceIndex) {
        List<CompletionObject.Choice> choices = apiCall.getBody().getChoices();

        if (choices.isEmpty()) {
            throw new IllegalArgumentException("No choices available in the response.");
        }

        String text = choices.get(choiceIndex).getText(); // Extract the text from the first (and possibly only) choice
        log.info("text: {}", text);

        try {
            return mapper.readValue(text, targetType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


//    private <T> T extract(ResponseEntity<ChatCompletion> apiCall, Class<T> targetType, int choiceIndex) {
//        ChatCompletion.Choice choice1 = execute(apiCall).getChoices().get(choiceIndex);
////        ChatCompletion.Choice choice = apiCall.getBody().getChoices().get(choiceIndex);
//        String content = choice1.getMessage().getContent();
//
//        try {
//            return mapper.readValue(content, targetType);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    private <T> T extract(ResponseEntity<ChatCompletion> apiCall, T targetType, int choiceIndex){
//
//        ChatCompletion.Choice choice = apiCall.getBody().getChoices().get(choiceIndex);
//        String content = choice.getMessage().getContent();
//        T t = null;
//        try {
//             t = (T) mapper.readValue(content, targetType.getClass());
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        return t;
//    }

}
