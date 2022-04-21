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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/agent")
public class AgentController {
  /**
   * @return response object
   */
  public ResponseEntity<List<AgentDto>> get() {
    log.info("calling hello endpoint");
    return ResponseEntity.ok().build();
  }

  /**
   * get agent by id
   *
   * @param uuid unique id of agent
   * @return agent object
   */
  @GetMapping("/{uuid}")
  public ResponseEntity<AgentDto> getAgentById(@PathVariable String uuid) {
    log.info("calling hello endpoint");
    validateUUID(uuid);
    return ResponseEntity.ok().build();
  }

  private void validateUUID(String uuid) {
    log.info("do nothing...");
  }
}
