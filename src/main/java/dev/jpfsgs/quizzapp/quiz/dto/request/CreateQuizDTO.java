package dev.jpfsgs.quizzapp.quiz.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateQuizDTO {
    @NotNull
    private String title;
    private String description;
    @NotEmpty
    private List<QuestionDTO> questions;


    @Data
    public static class QuestionDTO {
        @NotNull
        private String question;
        @NotEmpty
        private List<OptionDTO> options;
        @NotNull
        private Integer answerIndex;

        @Data
        public static class OptionDTO {
            @NotNull
            private Integer index;
            @NotNull
            private String option;
        }
    }
}
