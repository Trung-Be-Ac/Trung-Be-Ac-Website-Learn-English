package com.example.PJWebTa.Model.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.PJWebTa.Model.Entity.Course;
import com.example.PJWebTa.Model.Entity.Lesson;

public class LessonRepo {
    @Autowired
    CourseRepo courseRepo = new CourseRepo();
    // CREATE LESSON BY ID
    public static void addLesson(Lesson lesson) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement(
                "insert into lesson (course_id, lesson_description, lesson_name) values = (?,?,?)");
        ps.setInt(1, lesson.getCourse().getCourseID());
        ps.setString(2, lesson.getLessonDescription());
        ps.setString(3, lesson.getLessonName());
        ps.executeUpdate();
        con.close();
        ps.close();
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
    public void updateLesson(int lessonID, Course course, String lessonDescription, String lessonName)
            throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("delete from lesson where lesson_id = ?;");
        ps.setInt(1, lessonID);
        ps.setInt(2, course.getCourseID());
        ps.setString(3, lessonDescription);
        ps.setString(4, lessonName);
        ps.executeUpdate();
        con.close();
        ps.close();
    }

    // VIEW ALL LESSON
    public ArrayList <Lesson> viewAllLessons () throws Exception{
        ArrayList <Lesson> allLessons = new ArrayList<>();
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("select * from lesson;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int lessonId =  rs.getInt("lesson_id");
            Course course = courseRepo.getCoursebyID(rs.getInt("course_id"));
            String lessonDescription = rs.getString("lesson_description");
            String lessonName = rs.getString("lesson_name");
            Lesson lesson = new Lesson(lessonId, course, lessonDescription, lessonName);
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
        String lessonDescription = rs.getString("lesson_description");
        Lesson lesson = new Lesson(lessonID, course, lessonDescription, lessonDescription);
        con.close();
        ps.close();
        rs.close();
        return lesson;
    }
    // GET LESSON BY NAME
    public Lesson getLessonbyID(String name) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("select * from lesson where lesson_id = ?;");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int lessonID = rs.getInt("lesson_id");
        Course course = courseRepo.getCoursebyID(rs.getInt("course_id"));
        String lessonDescription = rs.getString("lesson_description");
        Lesson lesson = new Lesson(lessonID, course, lessonDescription, lessonDescription);
        con.close();
        ps.close();
        rs.close();
        return lesson;
    }
}
