package com.example.finale.service.jwt;

import com.example.finale.entities.Question;
import com.example.finale.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository repository;


    @Override
    public Integer saveQuestion(Question question) { return repository.save(question).getId_q();
    }

    @Override
    public List<Question> getAllQuestions() {
        return (List<Question>) repository.findAll();
    }

    @Override
    public Question getQuestionById(Integer id_q) {
        return repository.findById(id_q).get();
    }

    @Override
    public void deleteQuestion(Integer id_q) {
        repository.deleteById(id_q);
    }
}
