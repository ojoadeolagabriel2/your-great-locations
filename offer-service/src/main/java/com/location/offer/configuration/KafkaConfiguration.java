package com.location.offer.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.System.out;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class KafkaConfiguration {

    private static final String TEST_TOPIC = "locations.offers-service.public.offer.v1";

    private static final String TEST_TOPIC_GROUP = "locations.offers-service.group";
    private static final String TEST_TOPIC_GROUP_V2 = "locations.offers-service.group.v2";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 20_000)
    public void reportCurrentTime() {
        var message = "time is: " + dateFormat.format(new Date());

        log.info("sending.. {}", message);
        kafkaTemplate.send(TEST_TOPIC, message);
    }

    @KafkaListener(topics = TEST_TOPIC, groupId = TEST_TOPIC_GROUP)
    public void offersGroupConsumer(final String message) {
        out.println("received from group [" + TEST_TOPIC_GROUP + "] message: " + message);
    }

    @KafkaListener(topics = TEST_TOPIC, groupId = TEST_TOPIC_GROUP_V2)
    public void offersGroupConsumerV2(final String message) {
        out.println("received from group [" + TEST_TOPIC_GROUP_V2 + "] message: " + message);
    }
}