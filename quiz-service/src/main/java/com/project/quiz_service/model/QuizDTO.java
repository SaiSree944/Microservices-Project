package com.project.quiz_service.model;

import lombok.Data;

@Data
public class QuizDTO {
    private Integer numberOfQuestions;
    private String categoryId;
    private String title;

}
