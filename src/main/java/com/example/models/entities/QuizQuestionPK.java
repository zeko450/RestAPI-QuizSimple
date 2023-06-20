package com.example.models.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class QuizQuestionPK implements Serializable {
    private int quizId;
    private int questionId;


    public QuizQuestionPK() {
    }

    public QuizQuestionPK(int quizId,int questionId) {
        this.questionId = questionId;
        this.quizId = quizId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizQuestionPK that = (QuizQuestionPK) o;
        return questionId == that.questionId && quizId == that.quizId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, quizId);
    }
}
