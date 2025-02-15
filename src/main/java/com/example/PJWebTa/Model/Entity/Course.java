package com.example.PJWebTa.Model.Entity;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Course 
{
    private int courseID;
    private String courseName;
    private int courseLevel;
    private String courseDescription;
    private int courseTotallessons;
    private int courseFinishlesson;
    private double courseProcess;
    private User user;
    
    public Course(int courseID, String courseName, int courseLevel, String courseDescription, int courseTotallessons) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.courseDescription = courseDescription;
        this.courseTotallessons = courseTotallessons;
    }

    public Course(int courseID, String courseName, int courseLevel, String courseDescription, User user) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.courseDescription = courseDescription;
        this.user = user;
    }
    
}
