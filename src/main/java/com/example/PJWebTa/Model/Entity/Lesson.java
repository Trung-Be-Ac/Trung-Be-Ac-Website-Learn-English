package com.example.PJWebTa.Model.Entity;

import lombok.*;

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

    public Lesson(int lessonID, Course course, User user, String lessonDescription, String lessonName,
            Boolean lessonStatus) {
        this.lessonID = lessonID;
        this.course = course;
        this.user = user;
        this.lessonDescription = lessonDescription;
        this.lessonName = lessonName;
        this.lessonStatus = lessonStatus;
    }

}
