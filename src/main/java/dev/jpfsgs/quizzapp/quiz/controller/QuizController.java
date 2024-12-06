package dev.jpfsgs.quizzapp.quiz.controller;

import dev.jpfsgs.quizzapp.quiz.dto.request.CreateQuizDTO;
import dev.jpfsgs.quizzapp.quiz.dto.response.QuizDTO;
import dev.jpfsgs.quizzapp.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quizzes")
@Tag(name = "quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/")
    public String index() {
        return "Hello Quiz";
    }

    @GetMapping("/all")
    public List<QuizDTO> getAll() {
        return quizService.findAll();
    }

    @GetMapping("/{id}")
    public QuizDTO get(@PathVariable String id) {
        return quizService.findById(id);
    }

    @GetMapping("/user")
    public List<QuizDTO> getUserQuiz(JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getName());
        return quizService.findAllByUserId(userId);
    }

    @PostMapping("/create")
    public QuizDTO createQuiz(@RequestBody CreateQuizDTO quiz, JwtAuthenticationToken token) {
        UUID userId = UUID.fromString(token.getName());
        System.out.println("oi?");
        return quizService.save(quiz, userId);
    }
}
