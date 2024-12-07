package dev.jpfsgs.quizzapp.token.controller;

import dev.jpfsgs.quizzapp.token.dto.request.LoginRequestDTO;
import dev.jpfsgs.quizzapp.token.dto.response.LoginResponseDTO;
import dev.jpfsgs.quizzapp.token.service.TokenService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return tokenService.login(loginRequestDTO);
    }
}
