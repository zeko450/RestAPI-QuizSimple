package cgodin.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Quiz {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "QuizID", nullable = false)
    private int quizId;
    @Basic
    @Column(name = "Titre", nullable = true, length = 50)
    private String titre;
    @JsonIgnore
    @OneToMany(mappedBy = "quizByQuizId")
    private Collection<QuizQuestion> quizQuestionsByQuizId;

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        if (quizId != quiz.quizId) return false;
        if (titre != null ? !titre.equals(quiz.titre) : quiz.titre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = quizId;
        result = 31 * result + (titre != null ? titre.hashCode() : 0);
        return result;
    }

    public Collection<QuizQuestion> getQuizQuestionsByQuizId() {
        return quizQuestionsByQuizId;
    }

    public void setQuizQuestionsByQuizId(Collection<QuizQuestion> quizQuestionsByQuizId) {
        this.quizQuestionsByQuizId = quizQuestionsByQuizId;
    }
}
