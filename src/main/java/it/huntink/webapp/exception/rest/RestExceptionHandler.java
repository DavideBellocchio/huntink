package it.huntink.webapp.exception.rest;

import it.huntink.webapp.exception.FileStorageException;
import it.huntink.webapp.exception.TatuaggioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "it.huntink.webapp.controller.rest")
public class RestExceptionHandler {

    @ExceptionHandler(TatuaggioNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTatuaggioNotFound(TatuaggioNotFoundException ex){
        ErrorResponse errore = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errore);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errore = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> {
                    errore.put(error.getField(),error.getDefaultMessage());
                }
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errore);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorResponse> handleFileStorage(FileStorageException ex){
        ErrorResponse errore = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage() + ", causa: " + ex.getCause(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errore);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex){
        ErrorResponse errore = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errore);
    }

}
