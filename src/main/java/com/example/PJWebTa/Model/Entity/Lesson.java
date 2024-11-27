package com.example.PJWebTa.Model.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Lesson {
    private int lessonID;
    private Course course;
    private String lessonDescription;
    private String lessonName;
    
}
