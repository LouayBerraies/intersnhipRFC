package com.example.finale.controller;

import com.example.finale.entities.Question;
import com.example.finale.service.jwt.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questionDetails")
public class QuestionController {

    @Autowired
    private QuestionService service;
    @PostMapping("/saveQuestion")
    public ResponseEntity<String> saveQuestion( @RequestBody Question question){
        Integer id = service.saveQuestion(question);
        return  new ResponseEntity<String>("Question with '"+id+"' has been saved", HttpStatus.OK);

    }
    @GetMapping("/questionList")
    public ResponseEntity<List<Question>> getAllQuestionDetails(){
        List<Question> list = service.getAllQuestions();
        return new ResponseEntity<List<Question>>(list,HttpStatus.OK);
    }

    @GetMapping("/getQuestionById/{id_q}")
    public ResponseEntity<Question> getQuestionById( @PathVariable("id_q")  Integer id_q){
        Question std = service.getQuestionById(id_q);
        return new ResponseEntity<Question>(std,HttpStatus.OK);
    }

    @DeleteMapping("deleteQuestion/{id_q}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id_q") Integer id_q){
        service.deleteQuestion(id_q);
        return new ResponseEntity<String>("Question with '"+id_q+"' has been deleted",HttpStatus.OK);
    }
}
