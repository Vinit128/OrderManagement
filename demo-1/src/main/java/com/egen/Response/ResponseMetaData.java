package com.egen.Response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Data
    @Builder
    @RequiredArgsConstructor(staticName = "from")
    public class ResponseMetaData {

        int statusCode;
        @NonNull
        String statusMessage;
        @JsonInclude(NON_EMPTY)
        Map<String, Object> tags;

        public ResponseMetaData(int statusCode, String statusMessage, Map<String, Object> tags) {
            this.statusCode = statusCode;
            this.statusMessage = statusMessage;
            this.tags = tags;
        }


    }
