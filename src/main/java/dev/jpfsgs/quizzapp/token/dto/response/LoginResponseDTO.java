package dev.jpfsgs.quizzapp.token.dto.response;

import java.util.UUID;

public record LoginResponseDTO(
        String token,
        UUID uuid,
        String email,
        String name,
        Long expiresIn
) {
}
