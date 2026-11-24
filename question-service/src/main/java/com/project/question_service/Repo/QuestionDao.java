package com.project.question_service.Repo;


import com.project.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    @Query(value = "SELECT q.id FROM question q WHERE q.category = :categoryId ORDER BY RANDOM() LIMIT :numberOfQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionByCategory(String categoryId, Integer numberOfQuestions);

}
