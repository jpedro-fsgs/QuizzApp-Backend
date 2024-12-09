package dev.jpfsgs.quizzapp.token.service;

import dev.jpfsgs.quizzapp.token.dto.request.LoginRequestDTO;
import dev.jpfsgs.quizzapp.token.dto.response.LoginResponseDTO;
import dev.jpfsgs.quizzapp.user.model.User;
import dev.jpfsgs.quizzapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final UserRepository userRepository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.application.name}")
    private String appName;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.email()).orElseThrow(
                () -> new BadCredentialsException("Invalid email or password")
        );
        if (!passwordEncoder.matches(loginRequestDTO.password(), user.getHashedPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        Instant now = Instant.now();
        long expiresIn = 9000L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(appName)
                .subject(user.getId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(now)
                .build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDTO(jwtValue, user.getId(), user.getEmail(), user.getName(), expiresIn);
    }


}
