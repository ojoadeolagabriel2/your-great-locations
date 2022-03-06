package com.location.agent.error;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class SystemException extends RuntimeException {
  String code;
  String message;
  String detail;
}
