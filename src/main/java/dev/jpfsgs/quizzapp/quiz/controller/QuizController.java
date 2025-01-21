package dev.jpfsgs.quizzapp.quiz.controller;

import dev.jpfsgs.quizzapp.config.SecurityConfig;
import dev.jpfsgs.quizzapp.exception.ErrorDetails;
import dev.jpfsgs.quizzapp.quiz.dto.request.CreateQuizDTO;
import dev.jpfsgs.quizzapp.quiz.dto.response.QuizDTO;
import dev.jpfsgs.quizzapp.quiz.dto.response.QuizInfoDTO;
import dev.jpfsgs.quizzapp.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@Tag(name = "Quizzes", description = "Controller to manage Quizzes")
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/all")
    @Operation(summary = "Get all quizzes", description = "Retrieve a list of all quizzes.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of quizzes.")
    public List<QuizInfoDTO> getAll() {
        return quizService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Quiz by ID", description = "Retrieve a quiz by its unique ID.")
    @ApiResponse(responseCode = "200", description = "Quiz successfully found.")
    @ApiResponse(responseCode = "404", description = "Quiz not found.", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorDetails.class)
    ))
    public QuizDTO get(@PathVariable UUID id) {
        return quizService.findById(id);
    }

    @GetMapping("/user")
    @SecurityRequirement(name = SecurityConfig.SECURITY)
    @Operation(summary = "Get User's Quizzes", description = "Retrieve all quizzes created by the authenticated user.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the user's quizzes.")
    @ApiResponse(responseCode = "401", description = "Unauthorized access.", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorDetails.class)))
    public List<QuizDTO> getUserQuiz(@NotNull JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getName());
        return quizService.findAllByUserId(userId);
    }

    @PostMapping("/create")
    @SecurityRequirement(name = SecurityConfig.SECURITY)
    @Operation(summary = "Create a new quiz", description = "Create a new quiz associated with the authenticated user.")
    @ApiResponse(responseCode = "201", description = "Quiz successfully created.")
    @ApiResponse(responseCode = "401", description = "Unauthorized access.", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorDetails.class)
    ))
    public ResponseEntity<QuizDTO> createQuiz(@Valid @RequestBody CreateQuizDTO quiz, JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.save(quiz, userId));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = SecurityConfig.SECURITY)
    @Operation(summary = "Delete a quiz", description = "Delete a quiz by its ID if it belongs to the authenticated user.")
    @ApiResponse(responseCode = "204", description = "Quiz successfully deleted.")
    @ApiResponse(responseCode = "401", description = "Unauthorized access.", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorDetails.class)
    ))
    @ApiResponse(responseCode = "403", description = "Forbidden. User does not have permission to delete this quiz.", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorDetails.class)
    ))
    @ApiResponse(responseCode = "404", description = "Quiz not found.", content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorDetails.class)
    ))
    public ResponseEntity<Void> deleteQuiz(@PathVariable UUID id, JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getName());
        quizService.deleteQuizById(id, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}