package dev.jpfsgs.quizzapp.token.service;

import dev.jpfsgs.quizzapp.token.dto.request.LoginRequestDTO;
import dev.jpfsgs.quizzapp.token.dto.response.LoginResponseDTO;
import dev.jpfsgs.quizzapp.user.model.User;
import dev.jpfsgs.quizzapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final UserRepository userRepository;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Value("${spring.application.name}")
    private String appName;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.email()).orElseThrow(
                () -> new BadCredentialsException("Invalid email or password")
        );
        if (!passwordEncoder.matches(loginRequestDTO.password(), user.getHashedPassword())) {
            logger.warn("Login failed for email: {}", loginRequestDTO.email());
            throw new BadCredentialsException("Invalid email or password");
        }

        if(!user.getActive()){
            logger.warn("Login failed for email: {}", loginRequestDTO.email());
            throw new BadCredentialsException("User is not active");
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
        logger.info("Login successful for email: {}", loginRequestDTO.email());

        return new LoginResponseDTO(jwtValue, user.getId(), user.getEmail(), user.getName(), expiresIn);
    }


}
