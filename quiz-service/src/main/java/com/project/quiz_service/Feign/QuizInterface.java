package com.project.quiz_service.Feign;

import com.project.quiz_service.model.QuestionWrapper;
import com.project.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name="QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("/questions/generate")
    public ResponseEntity<List<Integer>> getRandomQuestionsByCategory(
            @RequestParam String categoryName, @RequestParam Integer numberOfQuestions) ;


    @PostMapping("/questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> questionIds)
      ;


    @PostMapping("/questions/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
      ;
    }

