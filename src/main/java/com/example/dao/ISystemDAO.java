package com.example.dao;
import com.example.models.Options;
import com.example.models.Question;
import com.example.models.Quiz;
import com.example.models.QuizQuestion;

import java.util.List;

public interface ISystemDAO {
    /**
     *Pour générer un quiz
     */
    Quiz addNewQuiz(String title);
    List<Question> addRandomQuestionsForQuiz(int QuizID, int N, String difficulty);

    /**
     *Pour passer un quiz
     */
    List<Question> getQuestionsForQuiz(int quizId);
    List<Options> optionsForQuestion(int questionId);
    List<Quiz> getNotUsedQuizzes();
    QuizQuestion updateQuizQuestion(int quizId, int questionId, int selectedOptionId);

    /**
     *Pour réviser un quiz
     */
    Options rightOptionsForQuestion(int questionId);
    List<Quiz> getUsedQuizzes();
    QuizQuestion getQuizQuestion(int quizId, int questionId);

}
