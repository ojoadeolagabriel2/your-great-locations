package com.locations.listing.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateListingResponseDto {
    @JsonProperty("id")
    Long listingId;
}