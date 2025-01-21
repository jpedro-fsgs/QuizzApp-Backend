package dev.jpfsgs.quizzapp.quiz.repository;

import dev.jpfsgs.quizzapp.quiz.dto.response.QuizInfoDTO;
import dev.jpfsgs.quizzapp.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    Optional<Quiz> findByTitle(String title);
    List<Quiz> findByUserId(UUID userId);
}
