package com.location.offer.configuration;

import com.location.offer.api.dto.ApiError;
import com.location.offer.domain.error.BusinessException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.time.LocalDateTime.now;
import static org.apache.commons.codec.digest.DigestUtils.md5Hex;
import static org.springframework.http.ResponseEntity.badRequest;

@Slf4j
@Configuration
@ControllerAdvice
public class ErrorManagementConfiguration {

    private static final String UNKNOWN_ERROR = "10001";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex) {
        ex.printStackTrace();
        var error = ApiError
                .builder()
                .code(UNKNOWN_ERROR)
                .message("could not complete request")
                .developerMessage(ex.getMessage())
                .build();

        return badRequest().body(error);
    }

    @SneakyThrows
    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<ApiError> handleGeneral(BusinessException ex) {
        String logMessageHash = md5Hex(now() + ex.getMessage());
        log.error(String.format("Error processing %s, see: %s", ex.getMessage(), logMessageHash), ex);

        ApiError error = ApiError
                .builder()
                .code(ex.getCode())
                .messageId(logMessageHash)
                .message(ex.getMessage())
                .build();

        return badRequest().body(error);
    }
}