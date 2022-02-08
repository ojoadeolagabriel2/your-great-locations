package com.locations.listing.domain.error;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {
    String code;
    String message;
}