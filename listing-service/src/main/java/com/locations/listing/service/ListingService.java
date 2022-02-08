package com.locations.listing.service;

import com.locations.listing.domain.model.Listing;

public interface ListingService {
    void update(Listing listing);
    Long save(Listing listing);
    Listing getById(Long id);
}