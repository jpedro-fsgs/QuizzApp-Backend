package dev.jpfsgs.quizzapp.quiz.controller;

import dev.jpfsgs.quizzapp.quiz.dto.CreateQuizDTO;
import dev.jpfsgs.quizzapp.quiz.model.Quiz;
import dev.jpfsgs.quizzapp.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String index() {
        return "Hello Quiz";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAll() {
        return ResponseEntity.ok().body(
                quizService.findAll()
        );
    }

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody CreateQuizDTO quiz) {
        Quiz response = quizService.save(quiz);
        return ResponseEntity.ok().body(response);
    }
}
