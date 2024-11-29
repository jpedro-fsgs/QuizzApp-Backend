package dev.jpfsgs.quizzapp.quiz.repository;

import dev.jpfsgs.quizzapp.quiz.model.Quiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@AutoConfigureDataMongo
public class QuizRepositoryTest {

    @Autowired
    private QuizRepository quizRepository;

    @Test
    void shouldSaveAndFindQuizByTitle() {
        Quiz quiz = new Quiz();
        quiz.setTitle("Spring Boot Basics");
        quiz.setDescription("Learn the basics of Spring Boot");
        quizRepository.save(quiz);

        Optional<Quiz> quizOptional = quizRepository.findByTitle("Spring Boot Basics");
        assertTrue(quizOptional.isPresent());
        assertEquals("Learn the basics of Spring Boot", quizOptional.get().getDescription());
    }

    @Test
    void shouldSaveAndDeleteQuiz() {
        Quiz quiz = new Quiz();
        quiz.setTitle("Spring Boot Basics");
        quiz.setDescription("Learn the basics of Spring Boot");
        quizRepository.save(quiz);

        Optional<Quiz> quizOptional = quizRepository.findByTitle("Spring Boot Basics");
        assertTrue(quizOptional.isPresent());

        quizRepository.delete(quizOptional.get());
        Optional<Quiz> quizOptional2 = quizRepository.findByTitle("Spring Boot Basics");
        assertFalse(quizOptional2.isPresent());
    }

    @Test
    void shouldSaveAndEditAndDeleteQuiz() {
        Quiz quiz = new Quiz();
        quiz.setTitle("Spring Boot Basics");
        quiz.setDescription("Learn the basics of Spring Boot");
        quizRepository.save(quiz);

        Optional<Quiz> quizOptional = quizRepository.findByTitle("Spring Boot Basics");
        assertTrue(quizOptional.isPresent());
        String quizId = quizOptional.get().getId();

        quizOptional.get().setTitle("Spring Boot Advanced");
        quizRepository.save(quizOptional.get());

        Optional<Quiz> quizOptional2 = quizRepository.findByTitle("Spring Boot Basics");
        assertFalse(quizOptional2.isPresent());

        Optional<Quiz> quizOptional3 = quizRepository.findByTitle("Spring Boot Advanced");
        assertTrue(quizOptional3.isPresent());
        assertEquals(quizOptional3.get().getId(), quizId);

        quizRepository.delete(quizOptional3.get());

        Optional<Quiz> quizOptional4 = quizRepository.findByTitle("Spring Boot Advanced");
        assertFalse(quizOptional4.isPresent());
    }

}
