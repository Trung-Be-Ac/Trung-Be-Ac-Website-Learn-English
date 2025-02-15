package com.example.PJWebTa.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class QuizTest {   
    private int quizID;
    private String quizTitle;
    private String quizText;
    private String quizCorrectanswer;
    private String quizA;
    private String quizB;
    private String quizC;
    private String quizD;
}
