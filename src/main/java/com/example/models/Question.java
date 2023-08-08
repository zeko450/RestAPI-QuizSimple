package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Question {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "QuestionID", nullable = false)
    private int questionId;
    @Basic
    @Column(name = "Enonce", nullable = true, length = 400)
    private String enonce;
    @Basic
    @Column(name = "Difficulte", nullable = true, length = 50)
    private String difficulte;

    @JsonIgnore
    @OneToMany(mappedBy = "questionByQuestionId")
    private Collection<Options> optionsByQuestionId;
    @JsonIgnore
    @OneToMany(mappedBy = "questionByQuestionId")
    private Collection<QuizQuestion> quizQuestionsByQuestionId;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getEnonce() {
        return enonce;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (questionId != question.questionId) return false;
        if (enonce != null ? !enonce.equals(question.enonce) : question.enonce != null) return false;
        if (difficulte != null ? !difficulte.equals(question.difficulte) : question.difficulte != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionId;
        result = 31 * result + (enonce != null ? enonce.hashCode() : 0);
        result = 31 * result + (difficulte != null ? difficulte.hashCode() : 0);
        return result;
    }

    public Collection<Options> getOptionsByQuestionId() {
        return optionsByQuestionId;
    }

    public void setOptionsByQuestionId(Collection<Options> optionsByQuestionId) {
        this.optionsByQuestionId = optionsByQuestionId;
    }

    public Collection<QuizQuestion> getQuizQuestionsByQuestionId() {
        return quizQuestionsByQuestionId;
    }

    public void setQuizQuestionsByQuestionId(Collection<QuizQuestion> quizQuestionsByQuestionId) {
        this.quizQuestionsByQuestionId = quizQuestionsByQuestionId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", enonce='" + enonce + '\'' +
                ", difficulte='" + difficulte + '\'' +
                ", optionsByQuestionId=" + optionsByQuestionId +
                ", quizQuestionsByQuestionId=" + quizQuestionsByQuestionId +
                '}';
    }
}
