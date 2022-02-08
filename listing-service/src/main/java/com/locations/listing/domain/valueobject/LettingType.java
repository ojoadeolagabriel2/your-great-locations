package com.locations.listing.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum LettingType {
    SHORT_TERM("SHORT_TERM", "Short Term"),
    LONG_TERM("LONG_TERM", "Long Term");

    String code;
    String description;

    public static Optional<LettingType> getByString(String code) {
        return Arrays
                .stream(LettingType.values()).filter(item -> item.getCode().equalsIgnoreCase(code))
                .findFirst();
    }
}