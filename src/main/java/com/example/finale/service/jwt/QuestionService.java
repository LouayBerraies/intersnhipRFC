package com.example.finale.service.jwt;

import com.example.finale.entities.Question;

import java.util.List;

public interface QuestionService {


    public Integer saveQuestion(Question question);

    public List<Question> getAllQuestions();
    public Question getQuestionById(Integer id_q);

    public void deleteQuestion(Integer id_q);


}
