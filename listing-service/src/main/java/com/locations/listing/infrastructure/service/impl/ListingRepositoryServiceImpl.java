package com.locations.listing.infrastructure.service.impl;

import com.locations.listing.domain.error.BusinessException;
import com.locations.listing.domain.model.ErrorCode;
import com.locations.listing.domain.model.Listing;
import com.locations.listing.infrastructure.converter.ListingEntityConverter;
import com.locations.listing.infrastructure.data.ListingEntity;
import com.locations.listing.infrastructure.repository.ListingRepository;
import com.locations.listing.infrastructure.service.ListingRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListingRepositoryServiceImpl implements ListingRepositoryService {

    private final ListingEntityConverter builder;
    private final ListingRepository listingRepository;

    @Override
    public void update(Listing listing) {
        ListingEntity listingEntity = listingRepository.findFirstByUuid((listing.getUuid()));
        ListingEntity dirtyListing = builder.getListingEntity(listingEntity, listing);
        listingRepository.save(dirtyListing);
    }

    /**
     * save listing
     *
     * @param listing instance
     * @return listing id
     */
    @Transactional
    public Long save(Listing listing) {
        ListingEntity listingEntity = builder.getListingEntity(listing);
        ListingEntity persistedListing = listingRepository.save(listingEntity);
        return persistedListing.getId();
    }

    @Override
    public Listing getById(Long id) {
        Optional<ListingEntity> possibleListingEntity = listingRepository.findById(id);

        if (possibleListingEntity.isPresent()) {
            return builder.getListingModel(possibleListingEntity.get());
        } else
            throw new BusinessException(ErrorCode.NOT_FOUND.getCode(), "listing not found");
    }
}
