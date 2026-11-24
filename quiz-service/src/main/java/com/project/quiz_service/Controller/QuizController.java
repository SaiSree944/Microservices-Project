package com.project.quiz_service.Controller;

import com.project.quiz_service.Service.QuizService;
import com.project.quiz_service.model.QuestionWrapper;
import com.project.quiz_service.model.QuizDTO;
import com.project.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizdto){
        return quizService.getAllQuestions(quizdto.getCategoryId(), quizdto.getNumberOfQuestions(), quizdto.getTitle()); // Return the list of questions for the quiz
    }


    @GetMapping("/quizId")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@RequestParam int quizId) {
        // Implement logic to retrieve questions for the specified quiz
        // You can use the quizId to fetch the associated questions from the database
        // Return the list of questions wrapped in a ResponseEntity
        return quizService.getQuestionsById(quizId);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable("id") int quizId, @RequestBody List<Response> responses) {
        // Implement logic to calculate the score based on user answers and the correct answers
        // You can use the quizId to fetch the correct answers from the database
        // Calculate the score and return it wrapped in a ResponseEntity
        return quizService.calculateResult(quizId, responses);
    }
}
