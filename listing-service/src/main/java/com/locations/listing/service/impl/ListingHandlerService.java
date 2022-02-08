package com.locations.listing.service.impl;

import com.locations.listing.domain.model.Listing;
import com.locations.listing.infrastructure.service.ListingRepositoryService;
import com.locations.listing.service.ListingService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Slf4j
@RequiredArgsConstructor
@Service("ListingServiceImpl")
public class ListingHandlerService implements ListingService {

    private final ListingRepositoryService listingRepository;

    @Timed(description = "time interval processing creating listing")
    public Long save(Listing listing) {
        listing.setUuid(randomUUID().toString());
        return listingRepository.save(listing);
    }

    @Timed(description = "time interval processing creating listing")
    public void update(Listing listing) {
        listingRepository.update(listing);
    }

    @Timed(description = "time interval processing get listing")
    public Listing getById(final Long id) {
        return listingRepository.getById(id);
    }
}