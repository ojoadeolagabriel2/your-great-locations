package com.location.agent.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix="appserver")
public class ServerProperties {
    private final int port = 12345;
}
