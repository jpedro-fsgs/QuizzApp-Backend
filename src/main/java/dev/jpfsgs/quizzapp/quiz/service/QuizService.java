package dev.jpfsgs.quizzapp.quiz.service;

import dev.jpfsgs.quizzapp.quiz.customexception.QuizNotFoundException;
import dev.jpfsgs.quizzapp.quiz.dto.mapper.QuizMapper;
import dev.jpfsgs.quizzapp.quiz.dto.request.CreateQuizDTO;
import dev.jpfsgs.quizzapp.quiz.dto.response.QuizDTO;
import dev.jpfsgs.quizzapp.quiz.model.Quiz;
import dev.jpfsgs.quizzapp.quiz.repository.QuizRepository;
import dev.jpfsgs.quizzapp.user.customexception.UserNotFoundException;
import dev.jpfsgs.quizzapp.user.model.User;
import dev.jpfsgs.quizzapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public QuizDTO findById(String id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new QuizNotFoundException("Quiz not found")
        );
        QuizDTO quizDTO = quizMapper.toQuizDTO(quiz);
        userRepository.findById(quiz.getUserId())
                .ifPresent(value -> quizDTO.setUser(value.getName()));
        return quizDTO;
    }

    public List<QuizDTO> findAll() {
        return quizRepository.findAll()
                .stream().map(quiz -> {
                    QuizDTO quizDTO = quizMapper.toQuizDTO(quiz);
                    userRepository.findById(quiz.getUserId())
                            .ifPresent(value -> quizDTO.setUser(value.getName()));
                    return quizDTO;
                })
                .toList();
    }

    public List<QuizDTO> findAllByUserId(UUID userId) {
        String userName = userRepository.findById(userId)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found")
                ).getName();

        return quizRepository.findByUserId(userId)
                .stream().map(quiz -> {
                    QuizDTO quizDTO = quizMapper.toQuizDTO(quiz);
                    quizDTO.setUser(userName);
                    return quizDTO;
                })
                .toList();
    }

    public QuizDTO save(CreateQuizDTO quizDTO, UUID userId) {
        Quiz quiz = quizMapper.toQuiz(quizDTO);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        quiz.setUserId(user.getId());
        quizRepository.save(quiz);
        logger.info("Quiz saved: {}, Id: {}", quiz.getTitle(), quiz.getId());
        return quizMapper.toQuizDTO(quiz);
    }
}
