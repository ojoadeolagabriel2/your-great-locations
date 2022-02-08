package com.locations.listing.domain.model;

import com.locations.listing.domain.valueobject.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Listing {
    String uuid;
    String listingTitle;
    OwnershipType listingOwnershipType;
    Double listedPricePerMonth;
    Double listedPricePerWeek;
    ListingCurrency currency;
    ZonedDateTime created;
    ZonedDateTime listedDate;
    LettingType letType;
    FurnishedType furnishedType;
    PropertyType propertyType;
    Short roomCount;
    Short bathroomCount;
    List<String> images;
    List<String> imageThumbNails;
    List<String> keyFeatures;
    String propertyDescription;
    String propertyDetail;
    ListingOwner listingOwner;
    ListingAddress listingAddress;
}