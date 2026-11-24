package com.project.quiz_service.Service;


import com.project.quiz_service.Feign.QuizInterface;
import com.project.quiz_service.Repo.QuizDao;
import com.project.quiz_service.model.Question;
import com.project.quiz_service.model.QuestionWrapper;
import com.project.quiz_service.model.Quiz;
import com.project.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuizService {



    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> getAllQuestions(String categoyId, int numberOfQuestions, String title) {
        List<Integer> questionIds= quizInterface.getRandomQuestionsByCategory(categoyId, numberOfQuestions).getBody();
//        List<Question> questions= questionDao.findRandomQuestionByCategory(categoyId, numberOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizDao.save(quiz);


        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(int quizId) {
        List<QuestionWrapper> questionWrapper = quizInterface.getQuestionsById(quizDao.findById(quizId).get().getQuestionIds()).getBody();
        return new ResponseEntity<>(questionWrapper, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int quizId, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
//        Quiz quiz = quizDao.findById(quizId).get();
//        List<Question> questions = quiz.getQuestions();
//        int right = 0;
//        int i = 0;
//        for(Response response : responses){
//            if(response.getResponse().equals(questions.get(i).getAnswer()))
//                right++;
//
//            i++;
//        }
        return score;
    }

}
