package com.locations.listing.infrastructure.repository;

import com.locations.listing.infrastructure.data.ListingKeyFeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingKeyFeaturesRepository extends JpaRepository<ListingKeyFeatureEntity, Long> {

}
