package com.location.offer.infrastructure;

import com.location.offer.infrastructure.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepositoryService extends JpaRepository<OfferEntity, Long> {
    OfferEntity findFirstByUuid(String uuid);
}