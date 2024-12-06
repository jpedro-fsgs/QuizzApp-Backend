package dev.jpfsgs.quizzapp.quiz.customexception;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException(String message) {
        super(message);
    }
}
