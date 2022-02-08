package com.locations.listing.infrastructure.data;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "listing_key_features")
public class ListingKeyFeatureEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feature_description")
    private String featureDescription;
    @Column(name = "listing_id")
    private Long listingId;
}