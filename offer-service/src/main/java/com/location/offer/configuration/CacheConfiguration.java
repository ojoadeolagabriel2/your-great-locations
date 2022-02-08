package com.location.offer.configuration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.util.HashMap;
import java.util.Map;

import static java.time.Duration.ofSeconds;
import static org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig;

@Configuration
public class CacheConfiguration {

    private static final long OFFERS_TTL = 10;
    private static final long LISTINGS_TTL = 10;

    @Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> {
            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
            configurationMap.put("offers", defaultCacheConfig().entryTtl(ofSeconds(OFFERS_TTL)));
            configurationMap.put("listings", defaultCacheConfig().entryTtl(ofSeconds(LISTINGS_TTL)));
            builder.withInitialCacheConfigurations(configurationMap);
        };
    }
}
