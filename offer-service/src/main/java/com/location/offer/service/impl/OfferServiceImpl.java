package com.location.offer.service.impl;

import com.location.offer.api.dto.OfferDto;
import com.location.offer.api.dto.OfferListingDto;
import com.location.offer.domain.error.BusinessException;
import com.location.offer.infrastructure.OfferRepositoryService;
import com.location.offer.infrastructure.entity.OfferEntity;
import com.location.offer.service.OfferService;
import com.location.offer.service.impl.handler.ListingHttpClientHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

import static com.location.offer.domain.valueobject.ErrorCode.RESOURCE_NOT_FOUND;
import static com.location.offer.domain.valueobject.ErrorCode.UPSTREAM_ENTITY_FAILURE;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

  private final OfferRepositoryService offerRepositoryService;
  private final ListingHttpClientHandler listingHttpClientHandler;

  public OfferDto getOfferById(Long offerId) {
    Optional<OfferEntity> possibleOfferEntity = offerRepositoryService.findById(offerId);
    if (possibleOfferEntity.isEmpty())
      throw BusinessException.builder()
          .code(RESOURCE_NOT_FOUND.getCode())
          .message("Offer not found")
          .detail("Offer not found for id: " + offerId)
          .build();

    OfferEntity offerEntity = possibleOfferEntity.get();
    try {
      OfferListingDto offerListingDto =
          listingHttpClientHandler.getListingById(offerEntity.getListingId());

      OfferDto offerDto = new OfferDto();
      offerDto.setOfferDate(ZonedDateTime.now());
      offerDto.setExpiryDate(offerEntity.getExpiryDate());
      offerDto.setUuid(offerEntity.getUuid());
      offerDto.setOfferStatus(offerEntity.getOfferStatus());
      offerDto.setListing(offerListingDto);
      return offerDto;
    } catch (Exception exception) {
      throw BusinessException.builder()
          .code(UPSTREAM_ENTITY_FAILURE.getCode())
          .message("Could not complete")
          .detail("Error connecting to listing service: " + exception.getMessage())
          .build();
    }
  }
}
