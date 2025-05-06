package com.example.PJWebTa.Model.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.PJWebTa.Model.Entity.Lesson;
import com.example.PJWebTa.Model.Entity.QuizTest;
import com.example.PJWebTa.Model.Entity.Test;

@Repository
public class QuizRepo {

    TestRepo testRepo = new TestRepo();

    // GET ALL QUIZ
    public ArrayList<QuizTest> getAllQuiz() throws Exception {
        ArrayList<QuizTest> allQuiz = new ArrayList<>();
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("select * from test");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int quizID = rs.getInt("quiz_id");
            String quizTitle = rs.getString("quiz_title");
            String quizText = rs.getString("quiz_text");
            String quizA = rs.getString("quiz_a");
            String quizB = rs.getString("quiz_b");
            String quizC = rs.getString("quiz_c");
            String quizD = rs.getString("quiz_d");
            String quizCorrectAnswer = rs.getString("quiz_correctanswer");
            int testID = rs.getInt("test_id");
            Test test = testRepo.getTestByID(testID);
            QuizTest quizTest = new QuizTest(quizID, quizTitle, quizText, quizA, quizB, quizC, quizD, quizCorrectAnswer,
                    test);
            allQuiz.add(quizTest);
        }
        rs.close();
        ps.close();
        con.close();
        return allQuiz;
    }

    // Get Quiz by ID
    public QuizTest getQuizByID(int id) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("SELECT * FROM quiz WHERE quiz_id = ?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        QuizTest quizTest = null;
        if (rs.next()) {
            int quizID = rs.getInt("quiz_id");
            String quizTitle = rs.getString("quiz_title");
            String quizText = rs.getString("quiz_text");
            String quizA = rs.getString("quiz_a");
            String quizB = rs.getString("quiz_b");
            String quizC = rs.getString("quiz_c");
            String quizD = rs.getString("quiz_d");
            String quizCorrectAnswer = rs.getString("quiz_correctanswer");
            int testID = rs.getInt("test_id");
            Test test = testRepo.getTestByID(testID);
            quizTest = new QuizTest(quizID, quizTitle, quizText, quizA, quizB, quizC, quizD, quizCorrectAnswer, test);
        }
        con.close();
        ps.close();
        rs.close();
        return quizTest;
    }

    // Add a new quiz
    public static void addQuiz(QuizTest quiz) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO quiz (quiz_title, quiz_text, quiz_a, quiz_b, quiz_c, quiz_d, quiz_correctanswer, test_id) "
                        +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, quiz.getQuizTitle());
        ps.setString(2, quiz.getQuizText());
        ps.setString(3, quiz.getQuizA());
        ps.setString(4, quiz.getQuizB());
        ps.setString(5, quiz.getQuizC());
        ps.setString(6, quiz.getQuizD());
        ps.setString(7, quiz.getQuizCorrectanswer());
        ps.setInt(8, quiz.getTest().getTestID()); // Lấy test_id từ QuizTest
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
    public static void updateQuiz(QuizTest quiz) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement(
                "UPDATE quiz SET quiz_title = ?, quiz_text = ?, quiz_a = ?, quiz_b = ?, quiz_c = ?, quiz_d = ?, quiz_correctanswer = ?, test_id = ? WHERE quiz_id = ?");
        ps.setString(1, quiz.getQuizTitle());
        ps.setString(2, quiz.getQuizText());
        ps.setString(3, quiz.getQuizA());
        ps.setString(4, quiz.getQuizB());
        ps.setString(5, quiz.getQuizC());
        ps.setString(6, quiz.getQuizD());
        ps.setString(7, quiz.getQuizCorrectanswer());
        ps.setInt(8, quiz.getTest().getTestID());
        ps.setInt(9, quiz.getQuizID());
        ps.executeUpdate();
        con.close();
        ps.close();
    }

    // GET QUIZ BY NAME
    public QuizTest getQuizByName(String title) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("SELECT * FROM quiz WHERE quiz_title = ?");
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();

        QuizTest quizTest = null;
        if (rs.next()) {
            int quizID = rs.getInt("quiz_id");
            String quizTitle = rs.getString("quiz_title");
            String quizText = rs.getString("quiz_text");
            String quizA = rs.getString("quiz_a");
            String quizB = rs.getString("quiz_b");
            String quizC = rs.getString("quiz_c");
            String quizD = rs.getString("quiz_d");
            String quizCorrectAnswer = rs.getString("quiz_correctanswer");
            int testID = rs.getInt("test_id");

            Test test = testRepo.getTestByID(testID);

            quizTest = new QuizTest(quizID, quizTitle, quizText, quizA, quizB, quizC, quizD, quizCorrectAnswer, test);
        }
        con.close();
        ps.close();
        rs.close();
        return quizTest;
    }

}
