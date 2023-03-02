package com.location.offer.configuration.props;

import com.location.offer.configuration.props.data.ListingEnvironment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.listing-service")
public class ListingServerConfiguration {
    private String selectedEnvironment;
    private List<ListingEnvironment> environments;
    public Optional<ListingEnvironment> getActiveEnvironmentConfiguration() {
        return environments.stream().filter(item -> selectedEnvironment.equals(item.getName())).findFirst();
    }
}