package com.location.offer.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor(force = true)
public class OfferDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("uuid")
    String uuid;
    @JsonProperty("offer_date")
    ZonedDateTime offerDate;
    @JsonProperty("offer_status")
    String offerStatus;
    @JsonProperty("expiry_date")
    ZonedDateTime expiryDate;
    @JsonProperty("listing")
    OfferListingDto listing;
}
