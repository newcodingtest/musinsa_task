package com.musinsa.exception;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalControllerAdvice {
    private final ObjectMapper objectMapper;

    private static final String ERROR_MESSAGE_DELIMITER = ", ";
    private static final String RESPONSE_DELIMITER = ". ";

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<?> handleGlobalException(final GlobalException e, final HttpServletRequest request)
            throws JsonProcessingException {
        final String exceptionSource = getExceptionSource(e);
        log.warn("source = {} , {} = {} code = {} message = {} info = {}", exceptionSource, request.getMethod(),
                request.getRequestURI(), e.getErrorCode().getCode(), e.getErrorCode().getMessage(),
                objectMapper.writeValueAsString(e.getErrorCode().getInfo()));

        final ErrorCode<?> errorCode = new ErrorCode<>(e.getErrorCode().getCode(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(errorCode);
    }

    private String getExceptionSource(final Exception e) {
        final StackTraceElement[] stackTrace = e.getStackTrace();
        if (stackTrace.length > 0) {
            return stackTrace[0].toString();
        }
        return "Unknown location";
    }
}