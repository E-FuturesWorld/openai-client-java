package com.efutures.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIError {

    private Error error;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error{

        private String message;
        private String type;
        private String param;
        private String code;
    }
}
