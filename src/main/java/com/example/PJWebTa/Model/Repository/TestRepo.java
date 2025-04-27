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
import com.example.PJWebTa.Model.Entity.Test;

@Repository
public class TestRepo {

    @Autowired
    LessonRepo lessonRepo;

    // Create Test
    public static void AddTest(Test test) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement(
                "insert into test (test_name,lesson_id,test_questiontype,test_timelimit) values (?,?,?,?);");
        ps.setString(1, test.getTestName());
        ps.setInt(2, test.getLesson().getLessonID());
        ps.setString(3, test.getTestQuestionType());
        ps.setTime(4, Time.valueOf(test.getTestTime()));
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    // Delete
    public static void DeleteTestByID(int ID) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("delete from test where test_id = ?");
        ps.setInt(1, ID);
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    // Update
    public static void UpdateTest(String testName, Lesson lesson, String testQuestionType,
            LocalTime testTime, int testID) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement(
                "update test set test_name = ?, lesson_id = ?, test_questiontype = ?, test_timelimit = ? where test_id = ?");
        ps.setString(1, testName);
        ps.setInt(2, lesson.getLessonID());
        ps.setString(3, testQuestionType);
        ps.setTime(4, Time.valueOf(testTime));
        ps.setInt(5, testID);
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    // Show All
    public ArrayList<Test> getAllTests() throws Exception {
        ArrayList<Test> allTests = new ArrayList<>();
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("select * from test");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int testID = rs.getInt("test_id");
            String testName = rs.getString("test_name");
            Lesson lesson = lessonRepo.getLessonbyID(rs.getInt("lesson_id"));
            String testQuestionType = rs.getString("test_questiontype");
            LocalTime testTime = rs.getTime("test_timelimit").toLocalTime();
            Test test = new Test(testID, testName, lesson, testQuestionType, testTime);
            allTests.add(test);
        }
        rs.close();
        ps.close();
        con.close();
        return allTests;
    }

    // Find By ID
    public Test getTestByID(int ID) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("select * from test where test_id = ?");
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        Test test = null;
        if (rs.next()) {
            int testID = rs.getInt("test_id");
            String testName = rs.getString("test_name");
            Lesson lesson = lessonRepo.getLessonbyID(rs.getInt("lesson_id"));
            String testQuestionType = rs.getString("test_questiontype");
            LocalTime testTime = rs.getTime("test_timelimit").toLocalTime();
            test = new Test(testID, testName, lesson, testQuestionType, testTime);
        }
        rs.close();
        ps.close();
        con.close();
        return test;
    }

    // Find By Name
    public Test getTestByName(String name) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("select * from test where test_name = ?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        Test test = null;
        if (rs.next()) {
            int testID = rs.getInt("test_id");
            String testName = rs.getString("test_name");
            Lesson lesson = lessonRepo.getLessonbyID(rs.getInt("lesson_id"));
            String testQuestionType = rs.getString("test_questiontype");
            LocalTime testTime = rs.getTime("test_timelimit").toLocalTime();
            test = new Test(testID, testName, lesson, testQuestionType, testTime);
        }
        rs.close();
        ps.close();
        con.close();
        return test;
    }
}
