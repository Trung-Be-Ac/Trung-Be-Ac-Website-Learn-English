package com.example.PJWebTa.Model.Entity;

import java.time.LocalTime;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Test {
    private int testID;
    private Quiz quiz;
    private Lesson lesson;
    private String testQuestionType;
    private LocalTime testTime;
}
