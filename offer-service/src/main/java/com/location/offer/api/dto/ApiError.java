package com.location.offer.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Value
@Builder
@JsonInclude(NON_NULL)
public class ApiError {
    @JsonProperty("code")
    String code;
    @JsonProperty("message")
    String message;
    @JsonProperty("developer_message")
    String developerMessage;
    @JsonProperty("reference")
    String messageId;
}