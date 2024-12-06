package dev.jpfsgs.quizzapp.quiz.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateQuizDTO {
    private String userId;
    private String title;
    private String description;
    private List<QuestionDTO> questions;


    @Data
    public static class QuestionDTO {
        private String question;
        private List<OptionDTO> options;
        private Integer answerIndex;

        @Data
        public static class OptionDTO {
            private Integer index;
            private String option;
            private Boolean isCorrect;
        }
    }
}
