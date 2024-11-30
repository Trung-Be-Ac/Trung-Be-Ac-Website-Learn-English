package com.example.PJWebTa.Model.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.PJWebTa.Model.Entity.Lesson;

public class LessonRepo {
    // Create Lesson
    public static void AddLesson(Lesson lesson) throws Exception {
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

    // DELETE LESSON
    public static void DeleteLessonByID (int id) throws Exception{
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("");
    }

}
