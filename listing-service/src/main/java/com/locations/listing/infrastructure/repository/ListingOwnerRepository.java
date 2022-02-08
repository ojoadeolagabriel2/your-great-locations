package com.locations.listing.infrastructure.repository;

import com.locations.listing.infrastructure.data.ListingOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingOwnerRepository  extends JpaRepository<ListingOwnerEntity, Long> {
}