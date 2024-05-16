package br.com.fiap.opengroup.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle("DataIntegrityViolationException");
        error.setLocalizedMessage( ex.getLocalizedMessage() );
        error.setEndpoint(request.getRequestURI() );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTitle("ResourceNotFoundException");
        error.setLocalizedMessage( ex.getLocalizedMessage() );
        error.setEndpoint(request.getRequestURI() );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle("MethodArgumentNotValidException");
        error.setLocalizedMessage( ex.getLocalizedMessage() );
        error.setEndpoint(request.getRequestURI() );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Data
    public static class ErrorResponse {
        private int status;
        private String message;
        private String localizedMessage;
        private String title;
        private String endpoint;

    }
}

