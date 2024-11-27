package dev.jpfsgs.quizzapp.quiz.service;

import dev.jpfsgs.quizzapp.quiz.model.Quiz;
import dev.jpfsgs.quizzapp.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    public Optional<Quiz> findById(String id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
