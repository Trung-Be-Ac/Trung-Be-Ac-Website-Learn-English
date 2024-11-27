package com.example.PJWebTa.Model.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Quiz {
    private int quizID;
    private String quizTiltle;
    private String quizText;
    private String quizCorrectanswer;
    private String quizA;
    private String quizB;
    private String quizC;
    private String quizD;
}
