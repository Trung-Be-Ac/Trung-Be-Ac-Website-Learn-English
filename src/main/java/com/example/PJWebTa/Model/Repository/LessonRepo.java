package com.example.PJWebTa.Model.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.PJWebTa.Model.Entity.Course;
import com.example.PJWebTa.Model.Entity.Lesson;
import com.example.PJWebTa.Model.Entity.User;

@Repository
public class LessonRepo {
    @Autowired
    CourseRepo courseRepo = new CourseRepo();
    UserRepo userRepo = new UserRepo();

    // private int lessonID;
    // private Course courseID;
    // private User userID;
    // private String lessonDescription;
    // private String lessonName;
    // private Boolean lessonStatus;

    // CREATE LESSON BY ID
    public static void addLesson(Lesson lesson) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement(
                "insert into lesson (course_id, user_id, lesson_description, lesson_name,lesson_topic) values = (?,?,?,?,?)");
        ps.setInt(1, lesson.getCourse().getCourseID());
        ps.setInt(2, lesson.getUser().getUserID());
        ps.setString(3, lesson.getLessonDescription());
        ps.setString(4, lesson.getLessonName());
        ps.setString(5, lesson.getLessonTopic());
        ps.executeUpdate();
        con.close();
        ps.close();
    }

    // ADD TOPIC
    public void addLessonTopic(Lesson lesson) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("UPDATE lesson SET lesson_topic = ? WHERE lesson_id = ?");
        {
            ps.setString(1, lesson.getLessonTopic());
            ps.setInt(2, lesson.getLessonID());
            ps.executeUpdate();
        }
    }

    // DELETE LESSON BY ID
    public static void deleteLessonByID(int id) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("delete from lesson where lesson_id = ?;");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    // UPDATE LESSON
    public void updateLesson(int lessonID, Course course, String lessonDescription, String lessonName,
            String lessonTopic)
            throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement(
                "update lesson set lesson_description = ?, lesson_name=?, lesson_topic = ? where lesson_id = ?;");
        ps.setString(1, lessonDescription);
        ps.setString(2, lessonName);
        ps.setString(3, lessonTopic);
        ps.setInt(4, course.getCourseID());
        ps.setInt(5, lessonID);
        ps.executeUpdate();
        con.close();
        ps.close();
    }

    // UPDATE TOPIC
    public void updateLessonTopic(int lessonID, String lessontopic) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("update lesson set lesson_topic = ? where lesson_id = ? ");
        ps.setInt(1, lessonID);
        ps.setString(2, lessontopic);
        ps.executeUpdate();
        con.close();
        ps.close();
    }

    // VIEW ALL LESSON
    public ArrayList<Lesson> viewAllLessons() throws Exception {
        ArrayList<Lesson> allLessons = new ArrayList<>();
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("select * from lesson;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int lessonId = rs.getInt("lesson_id");
            User userID = userRepo.getUserbyID(rs.getInt("user_id"));
            Course courseID = courseRepo.getCoursebyID(rs.getInt("course_id"));
            String lessonDescription = rs.getString("lesson_description");
            String lessonName = rs.getString("lesson_name");
            String lessonTopic = rs.getString("lesson_topic");
            Lesson lesson = new Lesson(lessonId, courseID, userID, lessonDescription, lessonName, lessonTopic);
            allLessons.add(lesson);
        }
        con.close();
        rs.close();
        ps.close();
        return allLessons;
    }

    // GET LESSON BY ID
    public Lesson getLessonbyID(int id) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("select * from lesson where lesson_id = ?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int lessonID = rs.getInt("lesson_id");
        Course course = courseRepo.getCoursebyID(rs.getInt("course_id"));
        User user = userRepo.getUserbyID(rs.getInt("user_id"));
        String lessonName = rs.getString("lesson_name");
        String lessonDescription = rs.getString("lesson_description");
        Boolean lessonStatus = rs.getBoolean("lesson_status");
        String lessonTopic = rs.getString("lesson_topic");
        Lesson lesson = new Lesson(lessonID, course, user, lessonDescription, lessonName, lessonStatus, lessonTopic);
        con.close();
        ps.close();
        rs.close();
        return lesson;
    }

    // GET LESSON BY NAME
    public Lesson getLessonbyName(String name) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("select * from lesson where lesson_id = ?;");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int lessonID = rs.getInt("lesson_id");
        Course course = courseRepo.getCoursebyID(rs.getInt("course_id"));
        User user = userRepo.getUserbyID(rs.getInt("user_id"));
        String lessonName = rs.getString("lesson_name");
        String lessonDescription = rs.getString("lesson_description");
        Boolean lessonStatus = rs.getBoolean("lesson_status");
        String lessonTopic = rs.getString("lesson_topic");
        Lesson lesson = new Lesson(lessonID, course, user, lessonDescription, lessonName, lessonStatus, lessonTopic);
        con.close();
        con.close();
        ps.close();
        rs.close();
        return lesson;
    }

    // Cập nhật trạng thái của bài học thành hoàn thành
    public void updateLessonAsCompleted(int lessonId) throws Exception {
        Class.forName(BaseConnection.nameClass);
        try (Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE lesson SET lesson_status = 1 WHERE lesson_id = ?;")) {
            ps.setInt(1, lessonId);
            ps.executeUpdate();
        }
    }
}
