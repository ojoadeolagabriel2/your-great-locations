package com.locations.listing.infrastructure.repository;

import com.locations.listing.infrastructure.data.ListingImageThumbnailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingImageThumbnailRepository extends JpaRepository<ListingImageThumbnailEntity, Long> {

}
