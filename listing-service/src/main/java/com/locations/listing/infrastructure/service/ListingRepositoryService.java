package com.locations.listing.infrastructure.service;

import com.locations.listing.domain.model.Listing;

public interface ListingRepositoryService {
    void update(Listing listing);
    Long save(Listing listing);
    Listing getById(Long id);
}
