package dev.jpfsgs.quizzapp.quiz.model;

import dev.jpfsgs.quizzapp.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "quiz")

public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String description;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<Question> questions;

    @Data
    public static class Question {
        private String question;
        private List<Option> options;
        private Integer answerIndex;

        @Data
        public static class Option {
            private Integer index;
            private String option;
            private Boolean isCorrect;
        }
    }
}