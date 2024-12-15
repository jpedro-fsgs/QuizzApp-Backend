package dev.jpfsgs.quizzapp.auth.dto.request;

public record LoginRequestDTO(
        String email,
        String password
) {
}
