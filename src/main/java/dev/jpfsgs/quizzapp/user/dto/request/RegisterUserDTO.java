package dev.jpfsgs.quizzapp.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserDTO(
        @NotBlank @Email String email,
        @NotBlank String name,
        @NotBlank @Size(min = 8) String password
) {
}
