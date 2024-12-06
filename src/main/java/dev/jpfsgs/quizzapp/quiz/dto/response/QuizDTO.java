package dev.jpfsgs.quizzapp.quiz.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class QuizDTO {

    private String id;
    private String user;
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
