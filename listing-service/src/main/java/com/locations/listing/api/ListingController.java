package com.locations.listing.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.locations.listing.api.converter.ListingConverter;
import com.locations.listing.api.dto.CreateListingResponseDto;
import com.locations.listing.api.dto.ListingDto;
import com.locations.listing.domain.error.BusinessException;
import com.locations.listing.domain.model.ErrorCode;
import com.locations.listing.domain.model.Listing;
import com.locations.listing.service.ListingService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/listing")
public class ListingController {

    private final ListingConverter converter;
    private final ListingService listingService;
    private final ObjectMapper mapper;

    @Operation(summary = "Create listing")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found listing successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request detected"),
            @ApiResponse(responseCode = "500", description = "Unknown error"),
    })
    @Timed(description = "time interval processing creating listing")
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateListingResponseDto> createListing(@RequestBody ListingDto listingDto) {

        Listing listing = converter.toModel(listingDto);
        long listingId = listingService.save(listing);
        return ok(CreateListingResponseDto.builder().listingId(listingId).build());
    }

    @Timed(description = "time interval processing patch")
    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public void patchListing(@PathVariable Long id, @RequestBody JsonPatch patch) {
        ListingDto listingDto = converter.toDto(listingService.getById(id));

        try {
            Listing updatedListing = converter.toModel(applyPatchToListingDto(patch, listingDto));
            listingService.update(updatedListing);
        } catch (JsonPatchException | JsonProcessingException e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.INTEGRATION_ERROR.getCode(), "invalid patch request");
        }
    }

    @Operation(summary = "Find listing by unique id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found listing successfully"),
            @ApiResponse(responseCode = "404", description = "Could not find listing"),
            @ApiResponse(responseCode = "500", description = "Unknown error"),
    })
    @Cacheable(cacheNames = "listing", key = "#listingId")
    @Timed(description = "time interval processing listing request")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ListingDto findListingById(@PathVariable("id") Long listingId) {

        log.info("searching listing with id {}", listingId);
        Listing listing = listingService.getById(listingId);
        log.info("searching listing with id {}.. done", listingId);
        return converter.toDto(listing);
    }

    private ListingDto applyPatchToListingDto(JsonPatch patch, ListingDto targetListing)
            throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(mapper.convertValue(targetListing, JsonNode.class));
        return mapper.treeToValue(patched, ListingDto.class);
    }
}
