package dev.jpfsgs.quizzapp.token.dto.request;

public record LoginRequestDTO(
        String email,
        String password
) {
}
