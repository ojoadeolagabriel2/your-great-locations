package com.locations.listing.domain.valueobject;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum PropertyType {
    APARTMENT,
    DETACHED,
    SEMI_DETACHED,
    FLAT,
    TERRACE,
    END_OF_TERRACE,
    COTTAGE,
    BUNGALOW;

    public static Optional<PropertyType> getByString(String propertyType) {
        return Arrays
                .stream(PropertyType.values()).filter(item -> item.toString().equalsIgnoreCase(propertyType))
                .findFirst();
    }
}