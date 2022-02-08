package com.locations.listing.api.converter.impl;

import com.locations.listing.api.converter.ListingConverter;
import com.locations.listing.api.dto.ListingDto;
import com.locations.listing.domain.error.BusinessException;
import com.locations.listing.domain.model.GeoLocation;
import com.locations.listing.domain.model.Listing;
import com.locations.listing.domain.model.ListingAddress;
import com.locations.listing.domain.model.ListingOwner;
import com.locations.listing.domain.valueobject.FurnishedType;
import com.locations.listing.domain.valueobject.LettingType;
import com.locations.listing.domain.valueobject.OwnershipType;
import com.locations.listing.domain.valueobject.PropertyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.locations.listing.domain.model.ErrorCode.INVALID_CONVERSION;
import static com.locations.listing.domain.valueobject.ListingCurrency.getByShortCode;
import static java.util.Optional.ofNullable;

@Slf4j
@Component
public class DefaultListingConverterImpl implements ListingConverter {

    public ListingDto toDto(Listing listing) {
        ListingDto listingDto = new ListingDto();
        listingDto.setUuid(listing.getUuid());
        listingDto.setListingOwnershipType(listing.getListingOwnershipType().name());
        listingDto.setListingTitle(listing.getListingTitle());
        listingDto.setListingCurrency(listing.getCurrency().getShortCode());
        listingDto.setListedPricePerMonth(listing.getListedPricePerMonth());
        listingDto.setListedPricePerWeek(listing.getListedPricePerWeek());
        listingDto.setCreated(listing.getCreated());
        listingDto.setListedDate(listing.getListedDate());
        listingDto.setLetType(listing.getLetType().getCode());
        listingDto.setFurnishedType(listing.getFurnishedType().name());
        listingDto.setPropertyType(listing.getPropertyType().name());
        listingDto.setRoomCount(listing.getRoomCount());
        listingDto.setBathroomCount(listing.getBathroomCount());
        listingDto.setPropertyDescription(listing.getPropertyDescription());
        listingDto.setPropertyDetail(listing.getPropertyDetail());
        listingDto.setKeyFeatures(listing.getKeyFeatures());
        listingDto.setImages(listing.getImages());
        listingDto.setImageThumbNails(listing.getImageThumbNails());
        listingDto.setListingOwnerName(listing.getListingOwner().getName());
        listingDto.setListingPhoneNumbers(listing.getListingOwner().getListingPhoneNumbers());
        listingDto.setListingOwnerPostCode(listing.getListingOwner().getListingOwnerPostCode());
        listingDto.setListingOwnerAddressLine(listing.getListingOwner().getListingOwnerAddressLine());
        listingDto.setListingOwnerBusinessDescription(listing.getListingOwner().getListingOwnerBusinessDescription());
        listingDto.setListingGeoLocationLongitude(listing.getListingOwner().getGeoLocation().getLongitude());
        listingDto.setListingGeoLocationLatitude(listing.getListingOwner().getGeoLocation().getLatitude());
        listingDto.setListingOwnerName(listing.getListingOwner().getName());
        listingDto.setListingAddressLine(listing.getListingAddress().getAddressLine());
        listingDto.setListingPostCode(listing.getListingAddress().getPostCode());
        return listingDto;
    };

    public Listing toModel(ListingDto listingDto) {
        log.info("found listing_date: {}", listingDto.getListedDate());
        return Listing.builder()
                .uuid(listingDto.getUuid())
                .listingOwnershipType(OwnershipType.valueOf(listingDto.getListingOwnershipType()))
                .listingTitle(listingDto.getListingTitle())
                .listedPricePerMonth(listingDto.getListedPricePerMonth())
                .currency(getByShortCode(listingDto.getListingCurrency())
                        .orElseThrow(() -> new BusinessException(INVALID_CONVERSION.getCode(), "unrecognized currency")))
                .listedPricePerWeek(listingDto.getListedPricePerWeek())
                .created(listingDto.getCreated())
                .listedDate(listingDto.getListedDate())
                .roomCount(listingDto.getRoomCount())
                .bathroomCount(listingDto.getBathroomCount())
                .images(listingDto.getImages())
                .imageThumbNails(listingDto.getImageThumbNails())
                .keyFeatures(ofNullable(listingDto.getKeyFeatures()).orElse(List.of("no key features provided")))
                .propertyDetail(listingDto.getPropertyDetail())
                .propertyDescription(listingDto.getPropertyDescription())
                .listingAddress(ListingAddress
                        .builder()
                        .postCode(listingDto.getListingPostCode())
                        .addressLine(listingDto.getListingAddressLine())
                        .build())
                .letType(LettingType.getByString(listingDto.getLetType())
                        .orElseThrow(() -> new BusinessException(INVALID_CONVERSION.getCode(), "invalid let_type")))
                .furnishedType(FurnishedType.getByString(listingDto.getFurnishedType())
                        .orElseThrow(() -> new BusinessException(INVALID_CONVERSION.getCode(), "invalid furnished_type")))
                .propertyType(PropertyType.getByString(listingDto.getPropertyType())
                        .orElseThrow(() -> new BusinessException(INVALID_CONVERSION.getCode(), "invalid property type")))
                .listingOwner(ListingOwner
                        .builder()
                        .name(listingDto.getListingOwnerName())
                        .listingPhoneNumbers(listingDto.getListingPhoneNumbers())
                        .listingOwnerPostCode(listingDto.getListingOwnerPostCode())
                        .listingOwnerAddressLine(listingDto.getListingOwnerAddressLine())
                        .listingOwnerBusinessDescription(listingDto.getListingOwnerBusinessDescription())
                        .geoLocation(GeoLocation
                                .builder()
                                .longitude(listingDto.getListingGeoLocationLongitude())
                                .latitude(listingDto.getListingGeoLocationLatitude())
                                .build()
                        )
                        .build()
                )
                .build();
    }
}
