package com.locations.listing.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("10001", "unknown error detected"),
    COULD_NOT_COMPLETE("10002", "could not complete request"),
    INTEGRATION_ERROR("10003", "integration error detected"),
    VALIDATION("40000", "validation error"),
    INVALID_CONVERSION("40001", "error converting input to type"),
    NOT_FOUND("40400", "not found");

    private String code;
    private String message;
}