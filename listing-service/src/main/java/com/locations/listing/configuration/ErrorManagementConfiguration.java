package com.locations.listing.configuration;

import com.locations.listing.api.dto.ApiError;
import com.locations.listing.api.error.ApiException;
import com.locations.listing.domain.error.BusinessException;
import com.locations.listing.domain.error.IntegrationException;
import com.locations.listing.domain.model.ErrorCode;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex) {
        ex.printStackTrace();
        var error = ApiError
                .builder()
                .code(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
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

    @SneakyThrows
    @ExceptionHandler(value = {IntegrationException.class})
    protected ResponseEntity<ApiError> handleGeneral(IntegrationException ex) {
        String logMessageHash = md5Hex(now() + ex.getMessage());
        log.error(String.format("Error processing api request %s, see: %s", ex.getMessage(), logMessageHash), ex);

        ApiError error = ApiError
                .builder()
                .code(ex.getCode())
                .messageId(logMessageHash)
                .message(ex.getMessage())
                .build();

        return badRequest().body(error);
    }

    @SneakyThrows
    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<ApiError> handleGeneral(ApiException ex) {
        String logMessageHash = md5Hex(now() + ex.getMessage());
        log.error(String.format("Error processing request %s, see: %s", ex.getMessage(), logMessageHash), ex);

        ApiError error = ApiError
                .builder()
                .code(ex.getCode())
                .messageId(logMessageHash)
                .message(ex.getMessage())
                .build();

        return badRequest().body(error);
    }
}