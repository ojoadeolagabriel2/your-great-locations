package com.locations.listing.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(force = true)
public class ListingDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("uuid")
    String uuid;
    @JsonProperty("listing_title")
    String listingTitle;
    @JsonProperty("listing_currency")
    String listingCurrency;
    @JsonProperty("listed_price_per_month")
    Double listedPricePerMonth;
    @JsonProperty("listed_price_per_week")
    Double listedPricePerWeek;
    @JsonProperty("listing_ownership_type")
    String listingOwnershipType;
    @JsonProperty("listing_address_line")
    String listingAddressLine;
    @JsonProperty("listing_post_code")
    String listingPostCode;
    @JsonProperty("created")
    ZonedDateTime created;
    @JsonProperty("listed_date")
    ZonedDateTime listedDate;
    @JsonProperty("let_type")
    String letType;
    @JsonProperty("furnished_type")
    String furnishedType;
    @JsonProperty("property_type")
    String propertyType;
    @JsonProperty("room_count")
    Short roomCount;
    @JsonProperty("bathroom_count")
    Short bathroomCount;
    @JsonProperty("images")
    List<String> images;
    @JsonProperty("image_thumbnails")
    List<String> imageThumbNails;
    @JsonProperty("key_features")
    List<String> keyFeatures;
    @JsonProperty("property_description")
    String propertyDescription;
    @JsonProperty("property_detail")
    String propertyDetail;
    @JsonProperty("listing_owner_name")
    String listingOwnerName;
    @JsonProperty("listing_phone_numbers")
    List<String> listingPhoneNumbers;
    @JsonProperty("listing_owner_post_code")
    String listingOwnerPostCode;
    @JsonProperty("listing_owner_address_line")
    String listingOwnerAddressLine;
    @JsonProperty("listing_owner_business_description")
    String listingOwnerBusinessDescription;
    @JsonProperty("listing_location_longitude")
    String listingGeoLocationLongitude;
    @JsonProperty("listing_location_latitude")
    String listingGeoLocationLatitude;
}
