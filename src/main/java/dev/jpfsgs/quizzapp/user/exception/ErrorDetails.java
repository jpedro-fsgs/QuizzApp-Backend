package dev.jpfsgs.quizzapp.user.exception;

import java.time.Instant;

public class ErrorDetails{
        Instant timestamp;
        String message;
        String details;

        public ErrorDetails(String message, String details) {
            this.timestamp = Instant.now();
            this.message = message;
            this.details = details;
        }
}
