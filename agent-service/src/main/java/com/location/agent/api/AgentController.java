package com.location.agent.api;

import com.location.agent.api.dto.AgentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/agent")
public class AgentController {
  /**
   * @return response object
   */
  @GetMapping
  public ResponseEntity<List<AgentDto>> get() {
    log.info("calling hello endpoint");
    return ok().build();
  }

  /**
   * get agent by id
   *
   * @param uuid unique id of agent
   * @return agent object
   */
  @GetMapping("/{uuid}")
  public ResponseEntity<AgentDto> getAgentById(@PathVariable String uuid) {
    log.info("calling getAgentById with uuid " + uuid);
    validateUUID(uuid);

    return ok(
        AgentDto.builder()
            .email("ojoadeolagabriel@gmail.com")
            .name("your great locations")
            .description("best in the business :)")
            .logoIdentifier("your_great_locations_small")
            .addressLine1("84 london")
            .addressLine2("nestiary street")
            .postCode("n115ur")
            .build());
  }

  private void validateUUID(String uuid) {
    log.info("do nothing... pass validation for {} now", uuid);
  }
}
