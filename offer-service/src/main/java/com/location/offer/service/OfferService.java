package com.location.offer.service;

import com.location.offer.api.dto.OfferDto;

public interface OfferService {
    OfferDto getOfferById(Long offerId);
}