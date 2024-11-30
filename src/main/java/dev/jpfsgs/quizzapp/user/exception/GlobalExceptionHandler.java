package dev.jpfsgs.quizzapp.user.exception;

import dev.jpfsgs.quizzapp.user.exception.custom.UserAlreadyExistsException;
import dev.jpfsgs.quizzapp.user.exception.custom.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), ex.toString());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), ex.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }
}
