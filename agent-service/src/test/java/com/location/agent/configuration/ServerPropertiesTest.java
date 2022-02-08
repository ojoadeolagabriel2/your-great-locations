package com.location.agent.configuration;

import com.location.agent.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@EnableAutoConfiguration(
    exclude = {
      DataSourceAutoConfiguration.class,
      DataSourceTransactionManagerAutoConfiguration.class,
      HibernateJpaAutoConfiguration.class,
      KafkaAutoConfiguration.class
    })
@TestPropertySource("classpath:application.yaml")
class ServerPropertiesTest {
  private static final int DEFAULT_PORT = 12345;
  @Autowired private ServerProperties serverProperties;

  @Test
  public void whenSimplePropertyQueriedThenReturnsPropertyValue() {
    assertEquals(DEFAULT_PORT, serverProperties.getPort(), "Incorrectly bound Username property");
  }
}
