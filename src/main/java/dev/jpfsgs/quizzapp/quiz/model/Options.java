package dev.jpfsgs.quizzapp.quiz.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Options {
    private Integer index;
    private String answer;
    private boolean correct;
}
