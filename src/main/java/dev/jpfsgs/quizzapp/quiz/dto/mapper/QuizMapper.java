package dev.jpfsgs.quizzapp.quiz.dto.mapper;

import dev.jpfsgs.quizzapp.quiz.dto.request.CreateQuizDTO;
import dev.jpfsgs.quizzapp.quiz.dto.response.QuizDTO;
import dev.jpfsgs.quizzapp.quiz.dto.response.QuizInfoDTO;
import dev.jpfsgs.quizzapp.quiz.model.Quiz;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    Quiz toQuiz(CreateQuizDTO createQuizDTO);
    QuizDTO toQuizDTO(Quiz quiz);
    QuizInfoDTO toQuizInfoDTO(Quiz quiz);
}
