package dev.jpfsgs.quizzapp.quiz.dto.mapper;

import dev.jpfsgs.quizzapp.quiz.dto.request.CreateQuizDTO;
import dev.jpfsgs.quizzapp.quiz.dto.response.QuizDTO;
import dev.jpfsgs.quizzapp.quiz.dto.response.QuizInfoDTO;
import dev.jpfsgs.quizzapp.quiz.model.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    Quiz toQuiz(CreateQuizDTO createQuizDTO);

    @Mapping(target = "user", source = "user.name")
    QuizDTO toQuizDTO(Quiz quiz);

    @Mapping(target = "user", source = "user.name")
    QuizInfoDTO toQuizInfoDTO(Quiz quiz);
}
