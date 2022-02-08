package com.location.offer.domain.model;

import com.location.offer.domain.valueobject.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Offer {
    String uuid;
    ZonedDateTime offerDate;
    OfferStatus offerStatus;
    ZonedDateTime expiryDate;
    OfferListing offerListing;
}