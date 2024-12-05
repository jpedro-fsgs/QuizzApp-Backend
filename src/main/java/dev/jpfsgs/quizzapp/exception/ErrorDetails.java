package dev.jpfsgs.quizzapp.exception;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ErrorDetails{
        Instant timestamp;
        String message;

        public ErrorDetails(String message) {
            this.timestamp = Instant.now();
            this.message = message;
        }
}
