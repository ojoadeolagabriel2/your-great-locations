package com.location.offer.api.v1;

import com.location.offer.api.dto.OfferListingDto;
import com.location.offer.api.dto.OfferDto;
import com.location.offer.service.OfferService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offer")
public class OfferController {

  private final OfferService offerService;

  @Timed(description = "interval for createOfferByListingId request")
  @PostMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<OfferDto> createDefaultOfferByListingId(@PathVariable("id") Long listingId) {
    log.info("create default offer for listing {}", listingId);
    return ok().build();
  }

  @Operation(summary = "find unique offer listing")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Found offer listing"),
        @ApiResponse(responseCode = "404", description = "Could not find offer listing"),
        @ApiResponse(responseCode = "500", description = "Unknown error"),
      })
  @Timed(description = "time interval processing offer listing request")
  @ResponseStatus(value = OK)
  @GetMapping(value = "/{offer_id}/listing/{listing_id}", produces = APPLICATION_JSON_VALUE)
  public OfferListingDto getOfferListing(
      @PathVariable("offer_id") Long offerId, @PathVariable("listing_id") Long listingId) {
    log.info("searching listing {} by offer id {}", listingId, offerId);
    return new OfferListingDto();
  }

  @Operation(summary = "find offer by id")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Found offer successfully"),
        @ApiResponse(responseCode = "404", description = "Could not find offer"),
        @ApiResponse(responseCode = "500", description = "Unknown error"),
      })
  @Timed(description = "time interval processing offer request")
  @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<OfferDto> getById(@PathVariable(name = "id") Long offerId) {
    log.info("getting offer by id {}", offerId);
    OfferDto offerDto = offerService.getOfferById(offerId);
    return ok(offerDto);
  }
}
