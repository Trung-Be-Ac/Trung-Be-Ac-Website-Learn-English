package com.example.PJWebTa.Model.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.springframework.stereotype.Repository;

import com.example.PJWebTa.Model.Entity.Course;
import com.example.PJWebTa.Model.Entity.Lesson;
import com.example.PJWebTa.Model.Entity.Quiz;
import com.example.PJWebTa.Model.Entity.Test;
import com.example.PJWebTa.Model.Entity.User;

@Repository
public class QuizRepo {

    public Quiz getQuizByID(int id) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("select * from quiz where quiz_id = ?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int quizID = rs.getInt("quiz_id");
        String quizTitle = rs.getString("quiz_title");
        String quizText = rs.getString("quiz_text");
        String quizCorrectanswer = rs.getString("quiz_Correctanswer");
        String quizA = rs.getString("quiz_a");
        String quizB = rs.getString("quiz_b");
        String quizC = rs.getString("quiz_c");
        String quizD = rs.getString("quiz_d");
        Quiz quiz = new Quiz(quizID, quizTitle, quizText, quizCorrectanswer, quizA, quizB, quizC, quizD);
        con.close();
        ps.close();
        rs.close();
        return quiz;
    }

    // Add a new quiz
    public static void addQuiz(Quiz quiz) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO quiz (quiz_title, quiz_text, quiz_a, quiz_b, quiz_c, quiz_d, quiz_correctanswer, user_id)\n"
                        +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, quiz.getQuizTitle());
        ps.setString(2, quiz.getQuizText());
        ps.setString(3, quiz.getQuizA());
        ps.setString(4, quiz.getQuizB());
        ps.setString(5, quiz.getQuizC());
        ps.setString(6, quiz.getQuizD());
        ps.setString(7, quiz.getQuizCorrectanswer());
        ps.setInt(8, quiz.getQuizID());
        ps.executeUpdate();
        con.close();
        ps.close();
    }

    // Delete a quiz by ID
    public static void deleteQuizByID(int quizID) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("DELETE FROM quiz WHERE quiz_id = ?");
        ps.setInt(1, quizID);
        ps.executeUpdate();
        con.close();
        ps.close();
    }

    // Update a quiz
    public static void updateQuiz(Quiz quiz) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement(
                "UPDATE quiz SET quiz_title = ?, quiz_text = ?, quiz_a = ?, quiz_b = ?, quiz_c = ?, quiz_d = ?, quiz_correctanswer = ? WHERE quiz_id = ?");
        ps.setString(1, quiz.getQuizTitle());
        ps.setString(2, quiz.getQuizText());
        ps.setString(3, quiz.getQuizA());
        ps.setString(4, quiz.getQuizB());
        ps.setString(5, quiz.getQuizC());
        ps.setString(6, quiz.getQuizD());
        ps.setString(7, quiz.getQuizCorrectanswer());
        ps.setInt(8, quiz.getQuizID());
        ps.executeUpdate();
        con.close();
        ps.close();
    }

    // GETQUIZBYNAME
    public static Quiz getQuizbyName(String title) throws ClassNotFoundException, SQLException {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("select * from `quiz` where quiz_title = ?");
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int quizID = rs.getInt("quiz_id");
            String quizTitle = rs.getString("quiz_title");
            String quizText = rs.getString("quiz_text");
            String quizCorrectAnswer = rs.getString("quiz_correctanswer");
            String quizA = rs.getString("quiz_A");
            String quizB = rs.getString("quiz_B");
            String quizC = rs.getString("quiz_C");
            String quizD = rs.getString("quiz_D");
            Quiz quiz = new Quiz(quizID, quizTitle, quizText, quizCorrectAnswer, quizA, quizB, quizC, quizD);
            con.close();
            rs.close();
            ps.close();
            return quiz;
        } else {
            con.close();
            rs.close();
            ps.close();
            return null;
        }
    }
}
