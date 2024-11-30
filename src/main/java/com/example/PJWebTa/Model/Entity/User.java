package com.example.PJWebTa.Model.Entity;

import java.sql.Date;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class User {
    private int userID;
    private String userName;
    private String userEmail;
    private int userLevel;
    private Date userDateJoined;
    private String userRole;
    private int user_type;
    //0. Ban 1. Normal 2.Premium
    private String userPicture;
    private String usernameA;
    private String passwordA;

    public User(int userID) {
        this.userID = userID;
    }
}
