package dev.jpfsgs.quizzapp.quiz.repository;

import dev.jpfsgs.quizzapp.quiz.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepository extends MongoRepository<Quiz, String> {
}
