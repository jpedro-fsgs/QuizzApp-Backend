package dev.jpfsgs.quizzapp.quiz.repository;

import dev.jpfsgs.quizzapp.quiz.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    Optional<Quiz> findByTitle(String title);
    List<Quiz> findByUserId(UUID userId);
}
