package com.locations.listing.infrastructure.converter;

import com.locations.listing.domain.error.BusinessException;
import com.locations.listing.domain.model.GeoLocation;
import com.locations.listing.domain.model.Listing;
import com.locations.listing.domain.model.ListingAddress;
import com.locations.listing.domain.model.ListingOwner;
import com.locations.listing.domain.valueobject.*;
import com.locations.listing.infrastructure.data.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static java.lang.String.join;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ListingEntityConverter {

  private static final ListingEntity EMPTY_LISTING_ENTITY = null;

  public Listing getListingModel(ListingEntity entity) {
    return Listing.builder()
        .uuid(entity.getUuid())
        .created(entity.getCreated())
        .listingOwnershipType(OwnershipType.valueOf(entity.getListingOwnershipType()))
        .listedDate(entity.getListedDate())
        .listingTitle(entity.getListingTitle())
        .currency(
            ListingCurrency.getByShortCode(entity.getListingCurrency())
                .orElseThrow(() -> new BusinessException("10001", "invalid currency retrieved")))
        .listedPricePerMonth(entity.getListedPricePerMonth())
        .listedPricePerWeek(entity.getListedPricePerWeek())
        .images(
            entity.getListingImageEntities().stream()
                .map(ListingImageEntity::getCode)
                .collect(toList()))
        .imageThumbNails(
            entity.getListingImageThumbnailEntities().stream()
                .map(ListingImageThumbnailEntity::getCode)
                .collect(toList()))
        .letType(
            LettingType.getByString(entity.getLetType())
                .orElseThrow(() -> new BusinessException("10001", "invalid let type retrieved")))
        .furnishedType(
            FurnishedType.getByString(entity.getFurnishedType())
                .orElseThrow(
                    () -> new BusinessException("10001", "invalid furnished type retrieved")))
        .propertyType(
            PropertyType.getByString(entity.getPropertyType())
                .orElseThrow(
                    () -> new BusinessException("10001", "invalid property type retrieved")))
        .keyFeatures(
            entity.getListingKeyFeatures().stream()
                .map(ListingKeyFeatureEntity::getFeatureDescription)
                .collect(toList()))
        .roomCount(entity.getRoomCount())
        .bathroomCount(entity.getBathroomCount())
        .propertyDescription(entity.getPropertyDescription())
        .propertyDetail(entity.getPropertyDetail())
        .listingAddress(
            ListingAddress.builder()
                .postCode(entity.getListingAddress().getPostCode())
                .addressLine(entity.getListingAddress().getAddressLine())
                .build())
        .listingOwner(
            ListingOwner.builder()
                .name(entity.getListingOwner().getName())
                .listingPhoneNumbers(
                    Arrays.stream(entity.getListingOwner().getListingPhoneNumbers().split(","))
                        .collect(toList()))
                .listingOwnerPostCode(entity.getListingOwner().getListingOwnerPostCode())
                .listingOwnerAddressLine(entity.getListingOwner().getListingOwnerAddressLine())
                .listingOwnerBusinessDescription(
                    entity.getListingOwner().getListingOwnerBusinessDescription())
                .geoLocation(
                    GeoLocation.builder()
                        .longitude(entity.getListingOwner().getLocationLongitude())
                        .latitude(entity.getListingOwner().getLocationLatitude())
                        .build())
                .build())
        .build();
  }

  public ListingEntity getListingEntity(ListingEntity entity, Listing listing) {
    if (isEmpty(entity)) entity = new ListingEntity();

    entity.setUuid(listing.getUuid());
    entity.setListingTitle(listing.getListingTitle());
    entity.setListingOwnershipType(listing.getListingOwnershipType().name());
    entity.setListingCurrency(listing.getCurrency().getShortCode());
    entity.setListedPricePerMonth(listing.getListedPricePerMonth());
    entity.setListedPricePerWeek(listing.getListedPricePerWeek());
    entity.setCreated(listing.getCreated());
    entity.setListedDate(listing.getListedDate());
    entity.setLetType(listing.getLetType().getCode());
    entity.setFurnishedType(listing.getFurnishedType().name());
    entity.setPropertyType(listing.getPropertyType().name());
    entity.setRoomCount(listing.getRoomCount());
    entity.setBathroomCount(listing.getBathroomCount());
    entity.setPropertyDescription(listing.getPropertyDescription());
    entity.setPropertyDetail(listing.getPropertyDetail());
    entity.setListingAddress(
        ListingAddressEntity.builder()
            .addressLine(listing.getListingAddress().getAddressLine())
            .postCode(listing.getListingAddress().getPostCode())
            .build());
    ListingOwner listingOwner = listing.getListingOwner();
    entity.setListingKeyFeatures(
        listing.getKeyFeatures().stream()
            .map(item -> ListingKeyFeatureEntity.builder().featureDescription(item).build())
            .collect(toSet()));

    entity.setListingImageEntities(
        listing.getImages().stream()
            .map(item -> ListingImageEntity.builder().code(item).build())
            .collect(toSet()));

    entity.setListingImageThumbnailEntities(
        listing.getImageThumbNails().stream()
            .map(item -> ListingImageThumbnailEntity.builder().code(item).build())
            .collect(toSet()));

    entity.setListingOwner(
        ListingOwnerEntity.builder()
            .name(listingOwner.getName())
            .listingPhoneNumbers(join(",", listingOwner.getListingPhoneNumbers()))
            .listingOwnerPostCode(listingOwner.getListingOwnerPostCode())
            .listingOwnerAddressLine(listingOwner.getListingOwnerAddressLine())
            .listingOwnerBusinessDescription(listingOwner.getListingOwnerBusinessDescription())
            .locationLatitude(listingOwner.getGeoLocation().getLatitude())
            .locationLongitude(listingOwner.getGeoLocation().getLongitude())
            .build());
    return entity;
  }

  public ListingEntity getListingEntity(Listing listing) {
    return getListingEntity(EMPTY_LISTING_ENTITY, listing);
  }
}
