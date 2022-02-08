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
@Table(name = "listing_addresses")
public class ListingAddressEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "post_code")
    private String postCode;
    @Column(name = "address_line")
    private String addressLine;
}