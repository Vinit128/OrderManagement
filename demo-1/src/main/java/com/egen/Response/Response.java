package com.egen.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import kafka.zookeeper.ResponseMetadata;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@Data
@Builder
@RequiredArgsConstructor(staticName = "from")
public class Response <T>{

    @JsonProperty("meta")
    @NonNull
    ResponseMetadata meta;

    @JsonProperty("data")
    @JsonInclude(NON_NULL)
    T data;

    public Response(ResponseMetadata meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    enum StatusMessage {
        SUCCESS,
        UNKNOWN_INTERNAL_ERROR,
        MISSING_REQUEST_PARAMETER,
        TOTES_SERVICE_INTERNAL_ERROR,
        EQUIPMENT_SERVICE_INTERNAL_ERROR
    }




}
