package com.example.models.dao;

import com.example.models.entities.*;
import jakarta.persistence.*;

import java.util.*;

public class SystemDAO implements ISystemDAO {

    Datamanager dataManager = null;



    public SystemDAO() {
        this.dataManager = Datamanager.getInstanceManager();
    }

    private List<Question> getQuestionsByDifficulty(String difficulty) {
        Query query = dataManager.manager.createQuery("SELECT q FROM Question q WHERE q.difficulte =:dif");
        query.setParameter("dif", difficulty);
        List<Question> questions = query.getResultList();
        return questions;
    }

    private List<Question> getRandomQuestions(List<Question> list, int N) {
        Random rand = new Random();
        // create a temporary list for storing
        // selected element
        List<Question> newList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            // take a random index between 0 to size
            // of given List
            int randomIndex = rand.nextInt(list.size());
            // add element in temporary list
            newList.add(list.get(randomIndex));
            // Remove selected element from original list
            list.remove(randomIndex);
        }
        return newList;
    }

    @Override
    public Quiz addNewQuiz(String title) {
        EntityTransaction transaction = dataManager.manager.getTransaction();
        try {
            transaction.begin();
            Quiz quiz = new Quiz();
            quiz.setTitre(title);
            dataManager.manager.persist(quiz);
            transaction.commit();
            return quiz;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Question> addRandomQuestionsForQuiz(int QuizID, int N, String difficulty) {
        EntityTransaction transaction = dataManager.manager.getTransaction();
        transaction.begin();
        try {
            Quiz quiz = dataManager.manager.find(Quiz.class, QuizID);
            if (quiz != null) {
                List<Question> questions = getQuestionsByDifficulty(difficulty);
                if (N > questions.size())
                    N = questions.size();
                List<Question> randomQuestions = getRandomQuestions(questions, N);
                for (Question question : randomQuestions) {
                    QuizQuestionPK quizQuestionPK = new QuizQuestionPK(quiz.getQuizId(), question.getQuestionId());
                    QuizQuestion quizQuestion = new QuizQuestion(quizQuestionPK, 0, quiz, question);
                    dataManager.manager.persist(quizQuestion);
                }
                transaction.commit();
                return randomQuestions;
            }

        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Question> getQuestionsForQuiz(int quizId) {
        Query query = dataManager.manager.createQuery(" SELECT q FROM Question q JOIN q.quizQuestionsByQuestionId qz WHERE qz.quizByQuizId.quizId=: Id");
        query.setParameter("Id", quizId);
        List<Question> questions = query.getResultList();
        return questions;
    }

    @Override
    public List<Options> optionsForQuestion(int questionId) {
        Query query = dataManager.manager.createQuery("SELECT o FROM Options o WHERE o.questionId = :questId");
        query.setParameter("questId", questionId);
        List<Options> options = query.getResultList();
        return options;
    }

    @Override
    //Si une question du quiz n'est pas complété, on considere que le quiz n'est pas validé
    public List<Quiz> getNotUsedQuizzes() {

        Query query = dataManager.manager.createQuery("SELECT q FROM Quiz q");
        List<Quiz> quizs = query.getResultList();
        List<Quiz> notusedQuizzes = new ArrayList<>();
        for (Quiz q: quizs) {
            List<QuizQuestion> listeQQ = (List<QuizQuestion>) q.getQuizQuestionsByQuizId();
            for (QuizQuestion tmp: listeQQ) {
                if(tmp.getSelectedOptionID() == 0){
                    notusedQuizzes.add(q);
                    break;
                }
            }
        }
        return notusedQuizzes;

    }


    @Override
    public QuizQuestion updateQuizQuestion(int quizId, int questionId, int selectedOptionId) {
        EntityTransaction transaction = dataManager.manager.getTransaction();
        transaction.begin();
        try {
            QuizQuestionPK quizQuestionPK = new QuizQuestionPK(quizId, questionId);
            QuizQuestion quizQuestion = dataManager.manager.find(QuizQuestion.class, quizQuestionPK);
            if (quizQuestion != null) {
                quizQuestion.setSelectedOptionID(selectedOptionId);
                transaction.commit();
                return quizQuestion;
            }
        } catch (Exception e) {
            transaction.rollback();
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Options rightOptionsForQuestion(int questionId) {
        Query query = dataManager.manager.createQuery("SELECT o FROM Options o WHERE o.questionId =:questId and o.estVrai = true ");
        query.setParameter("questId", questionId);
        Options option = (Options) query.getSingleResult();
        return option;
    }

    @Override
    public List<Quiz> getUsedQuizzes() {
        Query query = dataManager.manager.createQuery("SELECT q FROM Quiz q");
        List<Quiz> quizs = query.getResultList();
        List<Quiz> usedQuizs = new ArrayList<>();
        for (Quiz q: quizs) {
            List<QuizQuestion> listeQQ = (List<QuizQuestion>) q.getQuizQuestionsByQuizId();
            for (QuizQuestion tmp: listeQQ) {
                if(tmp.getSelectedOptionID() != 0){
                    usedQuizs.add(q);
                    break;
                }
            }
        }
        return usedQuizs;
    }

    @Override
    public QuizQuestion getQuizQuestion(int quizId, int questionId) {
        Query query = dataManager.manager.createQuery("SELECT qq FROM QuizQuestion qq WHERE qq.id.quizId =:quizId and qq.id.questionId =:questId");
        query.setParameter("quizId", quizId);
        query.setParameter("questId", questionId);
        QuizQuestion quizQuestion = (QuizQuestion) query.getSingleResult();
        return quizQuestion;
    }
}
