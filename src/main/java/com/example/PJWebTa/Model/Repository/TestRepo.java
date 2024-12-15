package com.example.PJWebTa.Model.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.PJWebTa.Model.Entity.Lesson;
import com.example.PJWebTa.Model.Entity.Quiz;
import com.example.PJWebTa.Model.Entity.Test;

@Repository
public class TestRepo {
        @Autowired
        Quiz quiz = new Quiz();
        @Autowired
        Lesson lesson = new Lesson();
        @Autowired
        QuizRepo quizRepo = new QuizRepo();
        LessonRepo lessonRepo = new LessonRepo();

        // Create Test
        public static void AddTest(Test test) throws Exception {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement(
                                "insert into test (test_name,quiz_id,lesson_id,test_questiontype,test_timelimit) values (?,?,?,?,?)");
                ps.setInt(1, test.getQuiz().getQuizID());
                ps.setString(2, test.getTestName());
                ps.setInt(3, test.getLesson().getLessonID());
                ps.setString(4, test.getTestQuestionType());
                ps.setTime(5, Time.valueOf(test.getTestTime()));
                ps.executeUpdate();
                con.close();
                ps.close();
        }

        // Delete
        public static void DeleteTestByID(int ID) throws Exception {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement(
                                "delete from test where test_id = ?;");
                ps.setInt(1, ID);
                ps.executeUpdate();
                con.close();
                ps.close();
        }

        // Update
        public static void UpdateTest(String testName, Quiz quizID, Lesson lessonID, String testQuestiontype,
                        LocalTime testTime,
                        int testID)
                        throws Exception {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement(
                                "update test set test_name = ?,quiz_id= ?,lesson_id= ?,test_questiontype= ?,test_timelimit = ? where test_id = ?;");
                ps.setString(1, testName);
                ps.setInt(2, quizID.getQuizID());
                ps.setInt(3, lessonID.getLessonID());
                ps.setString(4, testQuestiontype);
                ps.setTime(5, Time.valueOf(testTime));
                ps.setInt(6, testID);
                ps.executeUpdate();
                con.close();
                ps.close();
        }

        // Show All
        public ArrayList<Test> getAllTests() throws Exception {
                ArrayList<Test> allTests = new ArrayList<>();
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement("select * from Test");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        int testID = rs.getInt("test_id");
                        String testName = rs.getString("testName");
                        Quiz quizID = quizRepo.getQuizByID(rs.getInt("quiz_id"));
                        Lesson lessonID = lessonRepo.getLessonbyID(testID);
                        String testQuestiontype = rs.getString("test_questiontype");
                        LocalTime testTime = rs.getTime("test_time").toLocalTime();
                        Test test = new Test(testID, testName, quizID, lessonID, testQuestiontype, testTime);
                        allTests.add(test);
                }
                con.close();
                rs.close();
                ps.close();
                return allTests;
        }

        // Tìm Theo ID
        public Test getTestByID(int ID) throws Exception {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement("select * from test where test_id = ?");
                ps.setInt(1, ID);
                ResultSet rs = ps.executeQuery();
                rs.next();
                int testID = rs.getInt("test_id");
                String testName = rs.getString("testName");
                Quiz quizID = quizRepo.getQuizByID(rs.getInt("quiz_id"));
                Lesson lessonID = lessonRepo.getLessonbyID(testID);
                String testQuestiontype = rs.getString("test_questiontype");
                LocalTime testTime = rs.getTime("test_time").toLocalTime();
                Test test = new Test(testID, testName, quizID, lessonID, testQuestiontype, testTime);
                con.close();
                rs.close();
                ps.close();
                return test;
        }

        // Tìm Theo Name
        public Test getTestByName(String name) throws Exception {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement("select * from test where test_id = ?");
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                rs.next();
                int testID = rs.getInt("test_id");
                String testName = rs.getString("testName");
                Quiz quizID = quizRepo.getQuizByID(rs.getInt("quiz_id"));
                Lesson lessonID = lessonRepo.getLessonbyID(testID);
                String testQuestiontype = rs.getString("test_questiontype");
                LocalTime testTime = rs.getTime("test_time").toLocalTime();
                Test test = new Test(testID, testName, quizID, lessonID, testQuestiontype, testTime);
                con.close();
                rs.close();
                ps.close();
                return test;
        }

}
