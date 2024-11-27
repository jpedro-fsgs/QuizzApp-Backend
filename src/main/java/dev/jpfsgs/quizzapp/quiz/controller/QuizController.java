package dev.jpfsgs.quizzapp.quiz.controller;

import dev.jpfsgs.quizzapp.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {
    QuizService quizService;

    @GetMapping("/")
    public String index() {
        return "Hello Quiz";
    }
}
