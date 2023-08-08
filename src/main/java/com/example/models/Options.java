package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Options {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "OptionID", nullable = false)
    private int optionId;
    @Basic
    @Column(name = "texte", nullable = true, length = 255)
    private String texte;
    @Basic
    @Column(name = "EstVrai", nullable = false)
    private boolean estVrai;
    @Basic
    @Column(name = "QuestionID", nullable = true, insertable=false, updatable=false)
    private Integer questionId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "QuestionID", referencedColumnName = "QuestionID")
    private Question questionByQuestionId;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public boolean isEstVrai() {
        return estVrai;
    }

    public void setEstVrai(boolean estVrai) {
        this.estVrai = estVrai;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Options options = (Options) o;
        return optionId == options.optionId && estVrai == options.estVrai && Objects.equals(texte, options.texte) && Objects.equals(questionId, options.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId, texte, estVrai, questionId);
    }

    public Question getQuestionByQuestionId() {
        return questionByQuestionId;
    }

    public void setQuestionByQuestionId(Question questionByQuestionId) {
        this.questionByQuestionId = questionByQuestionId;
    }
}
