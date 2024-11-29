package dev.jpfsgs.quizzapp.quiz.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "quizzes")
public class Quiz {

    private String id;
    private String title;
    private String description;
    private List<Question> questions;

    @Data
    @Builder
    public static class Question {
        private String question;
        private List<Option> options;
        private Integer answerIndex;

        @Data
        @Builder
        public static class Option {
            private Integer index;
            private String option;
            private Boolean isCorrect;
        }
    }
}