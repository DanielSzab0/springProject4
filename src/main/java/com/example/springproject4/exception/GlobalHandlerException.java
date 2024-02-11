package com.example.springproject4.exception;

import com.example.springproject4.exception.error.ApiError;
import com.example.springproject4.exception.product.AddProductException;
import com.example.springproject4.exception.product.ProductNotFoundException;
import com.example.springproject4.exception.role.RoleNotFoundException;
import com.example.springproject4.exception.user.UserAlreadyTakenException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class GlobalHandlerException extends ResponseEntityExceptionHandler {
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError)
    {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handlerProductNotFoundException(ProductNotFoundException exception, HttpServletRequest request) {

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .message(exception.getMessage())
                .debugMessage((ExceptionUtils.getRootCauseMessage(exception)))
                .path(request.getRequestURI())
                .build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AddProductException.class)
    public ResponseEntity<Object> handleAddProductException(AddProductException exception, HttpServletRequest request) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage())
                .debugMessage((ExceptionUtils.getRootCauseMessage(exception)))
                .path(request.getRequestURI())
                .build();

        return buildResponseEntity(apiError);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, HttpServletRequest request) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .debugMessage((ExceptionUtils.getRootCauseMessage(exception)))
                .path(request.getRequestURI())
                .build();

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UserAlreadyTakenException.class)
    public ResponseEntity<Object> handleUserAlreadyTakenException(UserAlreadyTakenException exception, HttpServletRequest request) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage())
                .debugMessage((ExceptionUtils.getRootCauseMessage(exception)))
                .path(request.getRequestURI())
                .build();

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException exception, HttpServletRequest request) {
        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .debugMessage((ExceptionUtils.getRootCauseMessage(exception)))
                .path(request.getRequestURI())
                .build();

        return buildResponseEntity(apiError);
    }
}
