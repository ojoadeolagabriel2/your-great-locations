package com.location.offer.service.impl.handler;

import com.location.offer.api.dto.OfferListingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListingHttpClientHandler {

  private final WebClient listingWebClientV1;

  @Cacheable(value = "listings", key = "#offerId")
  public OfferListingDto getListingById(Long offerId) {

    return listingWebClientV1
        .get()
        .uri("/listing/" + offerId)
        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
        .retrieve()
        .bodyToMono(OfferListingDto.class)
        .block();
  }
}
