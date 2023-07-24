package com.location.agent.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
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
@ConditionalOnBean(KafkaAutoConfiguration.class)
public class KafkaConfiguration {

  private static final String TEST_TOPIC = "locations.agent-service.private.v1";
  private static final String TEST_TOPIC_GROUP = "locations.agent-service.group";

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  @Scheduled(fixedRate = 10_000)
  public void reportStatusAtInterval(){
    log.info("we are still running");
  }

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
}
