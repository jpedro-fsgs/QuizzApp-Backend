package dev.jpfsgs.quizzapp.quiz.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Integer id;
    private String question;
    private List<Options> options;
    private Integer answerIndex;
}
