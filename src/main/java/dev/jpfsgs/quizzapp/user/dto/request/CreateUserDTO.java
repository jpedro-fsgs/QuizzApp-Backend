package dev.jpfsgs.quizzapp.user.dto.request;

public record CreateUserDTO (
    String email,
    String name,
    String password
) {}
