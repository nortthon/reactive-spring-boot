package com.github.nortthon.http;

import com.github.nortthon.exceptions.UserNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFound(final UserNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("user.notFound");
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    @Getter
    @Setter
    private class ExceptionResponse {
        private String errorCode;
        private String errorMessage;
    }
}
