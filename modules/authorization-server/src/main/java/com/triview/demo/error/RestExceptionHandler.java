package com.triview.demo.error;

import com.triview.demo.model.error.ApiError;
import com.triview.demo.user.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, NOT_FOUND);
    }

}
