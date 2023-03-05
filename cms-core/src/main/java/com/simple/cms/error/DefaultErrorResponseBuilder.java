/* Copyright (C) MeaTech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.simple.cms.error;

import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultErrorResponseBuilder {

    private SimpleCmsErrorModel createErrorMessage(int statusValue, List<ErrorModel> errorModelList) {
        return SimpleCmsErrorModel.builder()
                .timestamp(LocalDateTime.now())
                .status(statusValue)
                .errorMessages(errorModelList)
                .build();
    }

    public SimpleCmsErrorModel handleError(MethodArgumentNotValidException exception, int statusValue) {
        List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage(), err.getObjectName()))
                .distinct()
                .collect(Collectors.toList());
        return createErrorMessage(statusValue,errorMessages);
    }

    public SimpleCmsErrorModel handleError(ValidationException exception, int statusValue) {
        var errorMessages = List.of(
                ErrorModel.builder()
                        .error(exception.getLocalizedMessage())
                        .message(exception.getClass().getName())
                        .build()
        );
        return createErrorMessage(statusValue,errorMessages);
    }

    public SimpleCmsErrorModel handleError(BindException exception, int statusValue) {
        List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage(), err.getObjectName()))
                .distinct()
                .collect(Collectors.toList());
        return createErrorMessage(statusValue,errorMessages);
    }

    public SimpleCmsErrorModel handleError(HttpMediaTypeNotSupportedException exception, int statusValue) {
        var errorMessages = List.of(
                ErrorModel.builder()
                        .error(exception.getLocalizedMessage())
                        .fieldName("content-type")
                        .rejectedValue(exception.getContentType())
                        .message("Supported content-types: " + Arrays.toString(exception.getSupportedMediaTypes().toArray()))
                        .build()
        );
        return createErrorMessage(statusValue,errorMessages);
    }

    public SimpleCmsErrorModel handleError(HttpMediaTypeNotAcceptableException exception, int statusValue) {
        var errorMessages = List.of(
                ErrorModel.builder()
                        .error(exception.getLocalizedMessage())
                        .fieldName("content-type")
                        .message("Supported content-types: " + Arrays.toString(exception.getSupportedMediaTypes().toArray()))
                        .build()
        );
        return createErrorMessage(statusValue,errorMessages);
    }

    public SimpleCmsErrorModel handleError(AccessDeniedException exception, int statusValue) {
        var errorMessages = List.of(
                ErrorModel.builder()
                        .rejectedValue(exception.getReason())
                        .error(exception.getLocalizedMessage())
                        .message(exception.getClass().getName())
                        .build()
        );
        return createErrorMessage(statusValue,errorMessages);
    }

    public SimpleCmsErrorModel handleError(HttpMessageNotReadableException exception, int statusValue) {
        var errorMessages = List.of(
                ErrorModel.builder()
                        .error(exception.getLocalizedMessage())
                        .message(exception.getClass().getName())
                        .build()
        );
        return createErrorMessage(statusValue,errorMessages);
    }

    public SimpleCmsErrorModel handleError(RuntimeException exception, int statusValue) {
        var errorMessages = List.of(
                ErrorModel.builder()
                        .error(exception.getLocalizedMessage())
                        .message(exception.getClass().getName())
                        .build()
        );
        return createErrorMessage(statusValue,errorMessages);
    }

    public SimpleCmsErrorModel handleError(Exception exception, int statusValue) {
        var errorMessages = List.of(
                ErrorModel.builder()
                        .error(exception.getLocalizedMessage())
                        .message(exception.getClass().getName())
                        .build()
        );
        return createErrorMessage(statusValue,errorMessages);
    }

    public SimpleCmsErrorModel handleError(ConstraintViolationException exception, int statusValue) {
        var errorMessages = List.of(
                ErrorModel.builder()
                        .rejectedValue(exception.getConstraintViolations())
                        .error(exception.getLocalizedMessage())
                        .message(exception.getClass().getName())
                        .build()
        );
        return createErrorMessage(statusValue,errorMessages);
    }

}
