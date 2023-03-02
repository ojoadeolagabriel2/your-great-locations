package com.location.offer.configuration;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.util.HashMap;
import java.util.Map;

import static java.time.Duration.ofSeconds;
import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

@Slf4j
@Configuration
public class CacheConfiguration {

    private static final long USER_COUNTER = 12;
    private static final long OFFERS_TTL = 10;
    private static final long LISTINGS_TTL = 10;

    @Builder
    @RequiredArgsConstructor
    static class Cell {
        private final String name;
        private final String email;
    }

    @Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        final String USER_NAME = "funmi", EMAIL = "funmi@yahoo.com";
        Cell userOnlyCell = Cell
                .builder()
                .name(USER_NAME)
                .email(EMAIL)
                .build();

        if ("funmi".equals(userOnlyCell.name)) {
            log.info("funmi cell was found");
        }

        return (builder) -> {
            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
            configurationMap.put("offers", defaultCacheConfig().entryTtl(ofSeconds(OFFERS_TTL)));
            configurationMap.put("listings", defaultCacheConfig().entryTtl(ofSeconds(LISTINGS_TTL)));
            builder.withInitialCacheConfigurations(configurationMap);
        };
    }
}
