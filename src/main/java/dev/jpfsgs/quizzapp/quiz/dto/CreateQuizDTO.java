package dev.jpfsgs.quizzapp.quiz.dto;

import dev.jpfsgs.quizzapp.quiz.model.Quiz;

import lombok.Data;

import java.util.List;

@Data
public class CreateQuizDTO {
    private String title;
    private String description;
    private List<QuestionDTO> questions;

    public Quiz toQuiz() {
        return Quiz.builder()
                .title(title)
                .description(description)
                .questions(
                        questions.stream().map(
                                question -> Quiz.Question.builder()
                                        .question(question.question)
                                        .answerIndex(question.answerIndex)
                                        .options(
                                                question.options.stream().map(
                                                        option -> Quiz.Question.Option.builder()
                                                                .index(option.index)
                                                                .option(option.option)
                                                                .isCorrect(option.isCorrect)
                                                                .build()
                                                ).toList()
                                        )
                                        .build()
                        ).toList()
                )
                .build();
    }

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
