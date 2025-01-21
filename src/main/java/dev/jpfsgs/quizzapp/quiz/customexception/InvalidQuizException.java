package dev.jpfsgs.quizzapp.quiz.customexception;

public class InvalidQuizException extends RuntimeException {
    public InvalidQuizException(String message) {
        super(message);
    }
}
