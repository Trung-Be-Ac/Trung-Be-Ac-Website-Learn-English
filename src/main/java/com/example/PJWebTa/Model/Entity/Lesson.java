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
    private User user;
    private String lessonDescription;
    private String lessonName;
    private Boolean lessonStatus;
    
    public Lesson(int lessonID, Course course, User user, String lessonDescription, String lessonName) {
        this.lessonID = lessonID;
        this.course = course;
        this.user = user;
        this.lessonDescription = lessonDescription;
        this.lessonName = lessonName;
    }
}
