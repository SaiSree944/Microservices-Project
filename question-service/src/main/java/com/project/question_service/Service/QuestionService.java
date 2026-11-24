package com.project.question_service.Service;


import com.project.question_service.Repo.QuestionDao;
import com.project.question_service.model.Question;
import com.project.question_service.model.QuestionWrapper;
import com.project.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public void addQuestion(Question question) {
        questionDao.save(question);
    }

    public Question getQuestionById(int id) {
        return questionDao.findById(id).orElse(null);
    }

    public void deleteQuestionById(int id) {
        questionDao.deleteById(id);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numberOfQuestions) {
        List<Integer> questionIds=questionDao.findRandomQuestionByCategory(categoryName,numberOfQuestions);
        return new ResponseEntity<>(questionIds, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> questionIds) {
        List<Question> questions=questionDao.findAllById(questionIds);
        List<QuestionWrapper> questionWrappers=new ArrayList<>();
        for(Question question:questions){
            QuestionWrapper questionWrapper=new QuestionWrapper();
            questionWrapper.setId(question.getId());
            questionWrapper.setTitle(question.getCategory());
            questionWrapper.setQuestion(question.getQuestion());
            questionWrapper.setOption1(question.getOption1());
            questionWrapper.setOption2(question.getOption2());
            questionWrapper.setOption3(question.getOption3());
            questionWrapper.setOption4(question.getOption4());
            questionWrappers.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScoreForQuiz(List<Response> responses) {
        List<Question> questions=new ArrayList<>();
        int score=0;
        for(Response response : responses) {

            Question question = questionDao.findById(response.getId()).get();
            if(question.getAnswer().equals(response.getResponse())){
                score++;
            }


        }
        return new ResponseEntity<>(score,HttpStatus.OK);

    }
}
