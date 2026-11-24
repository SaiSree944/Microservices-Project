package com.project.question_service.Controller;
import com.project.question_service.Service.QuestionService;
import com.project.question_service.model.Question;
import com.project.question_service.model.QuestionWrapper;
import com.project.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.IntegerSyntax;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/allquestions")
    public List<Question> getAllQuestions() {

        return questionService.getAllQuestions();
    }

    @PostMapping("/addquestion")
    public void addQuestion(@RequestBody Question question) {

        questionService.addQuestion(question);
    }
    @GetMapping("{id}")
    public Question getQuestionById(@PathVariable int id) {

        return questionService.getQuestionById(id);
    }
    @DeleteMapping("{id}")
    public void deleteQuestionById(@PathVariable int id) {
        questionService.deleteQuestionById(id);
    }

    //generate random questions based on category and number of questions
    //http://localhost:8080/api/questions/generate?categoryName=java&numberOfQuestions=5
    @GetMapping("/generate")
     public ResponseEntity<List<Integer>> getRandomQuestionsByCategory(
             @RequestParam String categoryName,@RequestParam Integer numberOfQuestions) {
        return questionService.getQuestionForQuiz(categoryName,numberOfQuestions);
     }
     @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionsById(questionIds);

     }
     @PostMapping("/score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
      return questionService.getScoreForQuiz(responses);
    }

    //generate score based on user answers and correct answers
    //generate quiz based on category and number of questions



}
