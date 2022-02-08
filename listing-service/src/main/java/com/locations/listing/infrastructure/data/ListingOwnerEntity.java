package com.locations.listing.infrastructure.data;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "listing_owner")
public class    ListingOwnerEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private String listingPhoneNumbers;
    @Column(name = "post_code")
    private String listingOwnerPostCode;
    @Column(name = "address_line")
    private String listingOwnerAddressLine;
    @Column(name = "business_description")
    private String listingOwnerBusinessDescription;
    @Column(name = "location_latitude")
    private String locationLongitude;
    @Column(name = "location_longitude")
    private String locationLatitude;
}