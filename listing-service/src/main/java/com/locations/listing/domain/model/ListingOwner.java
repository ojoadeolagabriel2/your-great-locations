package com.locations.listing.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ListingOwner {
    String name;
    List<String> listingPhoneNumbers;
    String listingOwnerPostCode;
    String listingOwnerAddressLine;
    String listingOwnerBusinessDescription;
    GeoLocation geoLocation;
}