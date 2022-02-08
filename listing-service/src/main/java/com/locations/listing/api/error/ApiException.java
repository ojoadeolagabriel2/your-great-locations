package com.locations.listing.api.error;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ApiException extends RuntimeException{
    private final String code;
    private final String message;
}
