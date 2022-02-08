package com.locations.listing.infrastructure.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "listings")
public class ListingEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;
    @Column(name = "listing_title")
    private String listingTitle;
    @Column(name = "listing_ownership_type")
    private String listingOwnershipType;
    @Column(name = "listing_currency")
    private String listingCurrency;
    @Column(name = "listed_price_per_month")
    private Double listedPricePerMonth;
    @Column(name = "listed_price_per_week")
    private Double listedPricePerWeek;
    @Column(name = "created")
    private ZonedDateTime created;
    @Column(name = "listed_date")
    private ZonedDateTime listedDate;
    @Column(name = "let_type")
    private String letType;
    @Column(name = "furnished_type")
    private String furnishedType;
    @Column(name = "room_count")
    private Short roomCount;
    @Column(name = "bathroom_count")
    private Short bathroomCount;
    @Column(name = "property_type")
    private String propertyType;
    @Column(name = "property_description")
    private String propertyDescription;
    @Column(name = "property_detail")
    private String propertyDetail;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "listing_id")
    private Set<ListingKeyFeatureEntity> listingKeyFeatures;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "listing_id")
    private Set<ListingImageEntity> listingImageEntities;

    @OneToMany(cascade = ALL)
    @JoinColumn(name = "listing_id")
    private Set<ListingImageThumbnailEntity> listingImageThumbnailEntities;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "listing_owner_id")
    private ListingOwnerEntity listingOwner;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "listing_address_id")
    private ListingAddressEntity listingAddress;
}