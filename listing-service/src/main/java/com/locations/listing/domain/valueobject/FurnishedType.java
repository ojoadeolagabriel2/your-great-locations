package com.locations.listing.domain.valueobject;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum FurnishedType {
    FURNISHED,
    UNFURNISHED;

    public static Optional<FurnishedType> getByString(String furnishedType) {
        return Arrays
                .stream(FurnishedType.values()).filter(item -> item.toString().equalsIgnoreCase(furnishedType))
                .findFirst();
    }
}