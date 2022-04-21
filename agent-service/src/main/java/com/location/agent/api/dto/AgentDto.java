package com.location.agent.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AgentDto {
    private static final long serialVersionUID = 1L;

    @JsonProperty("name")
    String name;
    @JsonProperty("type")
    String type;
    @JsonProperty("logoIdentifier")
    String logoIdentifier;
    @JsonProperty("description")
    String description;
    @JsonProperty("addressLine1")
    String addressLine1;
    @JsonProperty("addressLine2")
    String addressLine2;
    @JsonProperty("postCode")
    String postCode;
    @JsonProperty("buyingContactPhoneNumber")
    String buyingContactPhoneNumber;
    @JsonProperty("lettingContactPhoneNumber")
    String lettingContactPhoneNumber;
    @JsonProperty("email")
    String email;
    @JsonProperty("listing_location_longitude")
    String listingGeoLocationLongitude;
    @JsonProperty("listing_location_latitude")
    String listingGeoLocationLatitude;
}
