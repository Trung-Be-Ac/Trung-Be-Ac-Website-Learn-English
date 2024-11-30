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
    private User user;
}
