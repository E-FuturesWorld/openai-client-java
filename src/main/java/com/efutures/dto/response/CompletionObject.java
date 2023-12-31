package com.efutures.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CompletionObject {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    @Data
    public static class Choice {
        private String text;
        private int index;
        private String finish_reason;

        @JsonProperty("logprobs")
        private Object logProbs;
    }

    @Data
    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;

        @JsonProperty("completion_tokens")
        private int completionTokens;

        @JsonProperty("total_tokens")
        private int totalTokens;
    }
}

