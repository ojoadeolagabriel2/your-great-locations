package com.location.agent.configuration;

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

    private static final long OFFERS_TTL = 60;
    private static final String AGENTS_CACHE_LABEL = "agents";

    @Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> {
            Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
            configurationMap.put(AGENTS_CACHE_LABEL, defaultCacheConfig().entryTtl(ofSeconds(OFFERS_TTL)));
            builder.withInitialCacheConfigurations(configurationMap);
        };
    }
}
