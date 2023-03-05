/* Copyright (C) MeaTech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.simple.cms.error;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.nio.file.AccessDeniedException;

import static org.springframework.http.ResponseEntity.status;

@Data
@Slf4j
@Builder
@ControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
public class LedgerRestExceptionHandler {

    private final DefaultErrorResponseBuilder defaultErrorResponseBuilder;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SimpleCmsErrorModel> handleValidationError(final MethodArgumentNotValidException exception) {
        log.warn("Argument validation error.", exception);
        var result = defaultErrorResponseBuilder.handleError(exception, HttpStatus.BAD_REQUEST.value());
        return status(result.getStatus()).body(result);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SimpleCmsErrorModel> handleValidationError(final ValidationException exception) {
        log.warn("Validation error.", exception);
        var result = defaultErrorResponseBuilder.handleError(exception, HttpStatus.BAD_REQUEST.value());
        return status(result.getStatus()).body(result);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SimpleCmsErrorModel> handleValidationError(final BindException exception) {
        log.warn("Argument validation error.", exception);
        var result = defaultErrorResponseBuilder.handleError(exception, HttpStatus.BAD_REQUEST.value());
        return status(result.getStatus()).body(result);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<SimpleCmsErrorModel> handleHttpMediaTypeNotSupportedError(final HttpMediaTypeNotSupportedException exception) {
        log.warn("Authentication error: content-type not supported.", exception);
        var result = defaultErrorResponseBuilder.handleError(exception, HttpStatus.FORBIDDEN.value());
        return status(result.getStatus()).body(result);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<SimpleCmsErrorModel> handleAccessDeniedException(final AccessDeniedException exception) {
        log.warn("Authentication error: required role is missing.", exception);
        var result = defaultErrorResponseBuilder.handleError(exception, HttpStatus.FORBIDDEN.value());
        return status(result.getStatus()).body(result);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SimpleCmsErrorModel> handleDataTypeError(final HttpMessageNotReadableException exception) {
        log.warn("Message conversion error.", exception);
        var result = defaultErrorResponseBuilder.handleError(exception, HttpStatus.BAD_REQUEST.value());
        return status(result.getStatus()).body(result);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SimpleCmsErrorModel> handleHttpMediaTypeNotAcceptableError(final HttpMediaTypeNotAcceptableException exception) {
        log.warn("Response is not acceptable.", exception);
        var result = defaultErrorResponseBuilder.handleError(exception, HttpStatus.BAD_REQUEST.value());
        return status(result.getStatus()).body(result);
    }

    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SimpleCmsErrorModel> handleInternalError(final RuntimeException exception) {
        log.error("Illegal state or argument error.", exception);
        var result = defaultErrorResponseBuilder.handleError(exception, HttpStatus.BAD_REQUEST.value());
        return status(result.getStatus()).body(result);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<SimpleCmsErrorModel> handleRuntimeError(final RuntimeException exception) {
        log.error("Unexpected runtime error.", exception);
        var result = defaultErrorResponseBuilder.handleError(exception, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return status(result.getStatus()).body(result);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<SimpleCmsErrorModel> handleUnknownExceptionError(final Exception exception) {
        log.error("Unknown error.", exception);
        var result = defaultErrorResponseBuilder.handleError(exception, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return status(result.getStatus()).body(result);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SimpleCmsErrorModel> handleValidationError(final ConstraintViolationException exception) {
        log.warn("ConstraintViolationException error.", exception);
        var result = defaultErrorResponseBuilder.handleError(exception, HttpStatus.BAD_REQUEST.value());
        return status(result.getStatus()).body(result);
    }

}
