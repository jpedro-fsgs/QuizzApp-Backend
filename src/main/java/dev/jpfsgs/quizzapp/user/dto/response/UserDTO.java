package dev.jpfsgs.quizzapp.user.dto.response;

public record UserDTO (
        String email,
        String name,
        Boolean active
) {}
