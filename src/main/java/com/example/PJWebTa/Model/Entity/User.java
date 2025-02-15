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

    public User(String usernameA, String passwordA) {
        this.usernameA = usernameA;
        this.passwordA = passwordA;
    }

    public User(String userName, String userEmail, int userLevel, Date userDateJoined, String userRole, int user_type,
            String userPicture) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userLevel = userLevel;
        this.userDateJoined = userDateJoined;
        this.userRole = userRole;
        this.user_type = user_type;
        this.userPicture = userPicture;
    }

    public User(String userName, String userEmail, String userRole, String usernameA, String passwordA) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.usernameA = usernameA;
        this.passwordA = passwordA;
    }

    public User(int userID) {
        this.userID = userID;
    }
}
