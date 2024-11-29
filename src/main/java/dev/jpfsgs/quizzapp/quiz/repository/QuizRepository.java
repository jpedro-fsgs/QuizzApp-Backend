package dev.jpfsgs.quizzapp.quiz.repository;

import dev.jpfsgs.quizzapp.quiz.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    Optional<Quiz> findByTitle(String title);
}
