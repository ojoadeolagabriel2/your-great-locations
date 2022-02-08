package com.locations.listing.api.converter;

import com.locations.listing.api.dto.ListingDto;
import com.locations.listing.domain.model.Listing;

public interface ListingConverter {
   Listing toModel(ListingDto listingDto);
   ListingDto toDto(Listing listing);
}