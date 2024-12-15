package dev.jpfsgs.quizzapp.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Schema(description = "Details of an error response")
public class ErrorDetails{
        Instant timestamp;
        List<String> message;

        public ErrorDetails(String message) {
            this.timestamp = Instant.now();
            this.message = List.of(message);
        }

        public ErrorDetails(List<String> message) {
            this.timestamp = Instant.now();
            this.message = message;
        }
}