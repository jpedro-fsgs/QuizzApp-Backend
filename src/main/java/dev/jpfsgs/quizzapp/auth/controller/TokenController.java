package dev.jpfsgs.quizzapp.auth.controller;

import dev.jpfsgs.quizzapp.auth.dto.request.LoginRequestDTO;
import dev.jpfsgs.quizzapp.auth.dto.response.LoginResponseDTO;
import dev.jpfsgs.quizzapp.auth.service.TokenService;
import dev.jpfsgs.quizzapp.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate a user and return a token.")
    @ApiResponse(responseCode = "200", description = "Login successful, token generated.")
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized access. Invalid credentials.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDetails.class)
            )
    )
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return tokenService.login(loginRequestDTO);
    }
}
