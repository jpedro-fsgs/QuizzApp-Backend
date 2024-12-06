package dev.jpfsgs.quizzapp.user.dto.response;

public record UserDTO
        (
                String id,
                String email,
                String name,
                Boolean active
        ) {
}

