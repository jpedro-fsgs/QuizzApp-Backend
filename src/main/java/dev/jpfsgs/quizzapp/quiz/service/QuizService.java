package dev.jpfsgs.quizzapp.quiz.service;

import dev.jpfsgs.quizzapp.quiz.customexception.InvalidQuizException;
import dev.jpfsgs.quizzapp.quiz.customexception.QuizNotFoundException;
import dev.jpfsgs.quizzapp.quiz.dto.mapper.QuizMapper;
import dev.jpfsgs.quizzapp.quiz.dto.request.CreateQuizDTO;
import dev.jpfsgs.quizzapp.quiz.dto.response.QuizDTO;
import dev.jpfsgs.quizzapp.quiz.dto.response.QuizInfoDTO;
import dev.jpfsgs.quizzapp.quiz.model.Quiz;
import dev.jpfsgs.quizzapp.quiz.repository.QuizRepository;
import dev.jpfsgs.quizzapp.user.customexception.UserNotFoundException;
import dev.jpfsgs.quizzapp.user.model.User;
import dev.jpfsgs.quizzapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(QuizService.class);

    public QuizDTO findById(UUID id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new QuizNotFoundException("Quiz not found")
        );

        return quizMapper.toQuizDTO(quiz);
    }

    public List<QuizInfoDTO> findAll() {
        return quizRepository.findAll()
                .stream().map(quizMapper::toQuizInfoDTO)
                .toList();
    }

    public List<QuizDTO> findAllByUserId(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found")
                );

        return user.getQuizzes()
                .stream().map(quizMapper::toQuizDTO)
                .toList();
    }

    //Verify if the questions and options are not empty,
    // and if the answer index is valid
    private boolean validateQuiz(CreateQuizDTO quiz){
        return !quiz.getQuestions().isEmpty() &&
        quiz.getQuestions().stream().allMatch(question ->
            !question.getOptions().isEmpty() &&
            question.getAnswerIndex() < question.getOptions().size() &&
            question.getAnswerIndex() >= 0
        );
    }

    public QuizDTO save(CreateQuizDTO quizDTO, UUID userId) {
        if(!validateQuiz(quizDTO)){
            throw new InvalidQuizException("Quiz not valid");
        }

        Quiz quiz = quizMapper.toQuiz(quizDTO);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        quiz.setUser(user);
        quizRepository.save(quiz);
        logger.info("Quiz saved: {}, Id: {}", quiz.getTitle(), quiz.getId());
        return quizMapper.toQuizDTO(quiz);
    }

    public void deleteQuizById(UUID quizId, UUID userId) {

        Quiz quiz = quizRepository.findById(quizId).orElseThrow(
                () -> new QuizNotFoundException("Quiz not found")
        );
        if (!quiz.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Access denied");
        }
        quizRepository.delete(quiz);
        logger.info("Quiz deleted: Id: {}", quizId);
    }

}
