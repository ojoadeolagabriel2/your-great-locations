package com.location.offer.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(force = true)
public class OfferListing implements Serializable {
    private static final long serialVersionUID = 1L;

    String uuid;
    String listingTitle;
    String listingCurrency;
    Double listedPricePerMonth;
    Double listedPricePerWeek;
    String listingOwnershipType;
    String listingAddressLine;
    String listingPostCode;
    ZonedDateTime created;
    ZonedDateTime listedDate;
    String letType;
    String furnishedType;
    String propertyType;
    Short roomCount;
    Short bathroomCount;
    List<String> images;
    List<String> imageThumbNails;
    List<String> keyFeatures;
    String propertyDescription;
    String propertyDetail;
    String listingOwnerName;
    List<String> listingPhoneNumbers;
    String listingOwnerPostCode;
    String listingOwnerAddressLine;
    String listingOwnerBusinessDescription;
    String listingGeoLocationLongitude;
    String listingGeoLocationLatitude;
}
