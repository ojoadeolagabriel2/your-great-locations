package com.locations.listing.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum ListingCurrency {
    GBP("£", "GBP", "British Pound"),
    USD("$", "USD", "United States Dollar");

    final String symbol;
    final String shortCode;
    final String description;

    public static Optional<ListingCurrency> getByShortCode(String code) {
        return Arrays
                .stream(ListingCurrency.values()).filter(item -> code.equals(item.shortCode))
                .findFirst();
    }
}
