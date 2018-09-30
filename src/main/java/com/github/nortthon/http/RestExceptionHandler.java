package com.github.nortthon.http;

import com.github.nortthon.exceptions.UserNotFoundException;
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

    private class ExceptionResponse {
        private String errorCode;
        private String errorMessage;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
