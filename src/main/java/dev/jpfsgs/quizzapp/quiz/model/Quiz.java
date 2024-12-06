package dev.jpfsgs.quizzapp.quiz.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "quizzes")
public class Quiz {

    @Id
    private String id;
    private UUID userId;
    private String title;
    private String description;
    private List<Question> questions;

    @Data
    public static class Question {
        private String question;
        private List<Option> options;
        private Integer answerIndex;

        @Data
        public static class Option {
            private Integer index;
            private String option;
            private Boolean isCorrect;
        }
    }
}