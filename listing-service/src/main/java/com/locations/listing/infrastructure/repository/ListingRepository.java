package com.locations.listing.infrastructure.repository;

import com.locations.listing.infrastructure.data.ListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<ListingEntity, Long> {
    ListingEntity findFirstByUuid(String uuid);
}
