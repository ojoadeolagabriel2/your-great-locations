package com.location.offer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.location.offer.configuration.props.ListingServerConfiguration;
import com.location.offer.configuration.props.data.ListingEnvironment;
import com.location.offer.domain.error.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.location.offer.configuration.valueobjects.CustomHeader.API_HEADER_VERSION_HEADER;
import static com.location.offer.domain.valueobject.ErrorCode.BAD_REQUEST;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

  private final ListingServerConfiguration configuration;
  private static final Boolean STATE_DISABLED = false;
  private static final Short DEFAULT_API_VERSION = 1;

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(FAIL_ON_NULL_FOR_PRIMITIVES, STATE_DISABLED);
    mapper.configure(WRITE_DATES_AS_TIMESTAMPS, STATE_DISABLED);
    mapper.registerModule(new JavaTimeModule());
    return mapper;
  }

  @Bean
  public WebClient listingWebClientV1() {
    Optional<ListingEnvironment> configuration =
        this.configuration.getActiveEnvironmentConfiguration();

    if (configuration.isPresent())
      return WebClient.builder()
          .baseUrl(configuration.get().getUrl())
          .defaultHeader(API_HEADER_VERSION_HEADER, DEFAULT_API_VERSION.toString())
          .build();
    else
      throw new BusinessException(
          BAD_REQUEST.name(), "missing listing environment", EMPTY);
  }
}
