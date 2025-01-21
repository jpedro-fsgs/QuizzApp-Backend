package dev.jpfsgs.quizzapp.quiz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizInfoDTO {

    private String id;
    private String user;
    private String title;
    private String description;

}
