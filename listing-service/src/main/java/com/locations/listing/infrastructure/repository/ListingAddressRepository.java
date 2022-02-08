package com.locations.listing.infrastructure.repository;

import com.locations.listing.infrastructure.data.ListingImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingAddressRepository extends JpaRepository<ListingImageEntity, Long> {
}
