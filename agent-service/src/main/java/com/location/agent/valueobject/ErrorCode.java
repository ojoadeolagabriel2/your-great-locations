package com.location.agent.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  // system errors
  INVALID_REQUEST("invalid_request"),
  UPSTREAM_ENTITY_FAILURE("upstream_entity_failure"),
  FEATURE_DISABLED("feature_disabled"),
  RATE_LIMIT_EXCEEDED("rate_limit_exceeded"),
  BAD_REQUEST("bad_request"),
  INVALID_DOCUMENT_STRUCTURE("invalid_document_structure"),
  VERSION_NOT_FOUND("version_not_found"),
  REQUEST_TOO_LARGE("request_entity_too_large"),
  RESOURCE_NOT_FOUND("resource_not_found");

  private String code;
}
