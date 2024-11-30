package com.example.PJWebTa.Model.Repository;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.PJWebTa.Model.Entity.Course;
import com.example.PJWebTa.Model.Entity.User;

@Repository
public class CourseRepo {
        @Autowired
        UserRepo userRepo = new UserRepo(); // Không dùng được ở hàm static

        // CREATE COURSE
        public static void AddCourse(Course course) throws Exception {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement(
                                "insert into course (course_name, course_level, course_description,user_id) values (?,?,?,?)");
                ps.setString(1, course.getCourseName());
                ps.setInt(2, course.getCourseLevel());
                ps.setString(3, course.getCourseDescription());
                ps.setInt(4, course.getUser().getUserID());
                ps.executeUpdate();
                ps.close();

        }

        // DELETE COURSE
        public static void DeleteCoursebyID(int id) throws Exception {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement(
                                "delete from course where course_id=?;");
                ps.setInt(1, id);
                ps.executeUpdate();
                con.close();
                ps.close();
        }

        // VIEW ALL COURSE
        public ArrayList<Course> Viewallcourse() throws Exception {
                ArrayList<Course> allCourse = new ArrayList<>();
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement(
                                "select * from course;");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        int courseID = rs.getInt("course_id");
                        String courseName = rs.getString("course_name");
                        int courseLevel = rs.getInt("course_level");
                        String courseDescription = rs.getString("course_description");
                        User userID = userRepo.getUserbyID(rs.getInt("user_id"));
                        Course course = new Course(courseID, courseName, courseLevel, courseDescription, userID);
                        allCourse.add(course);
                }
                con.close();
                ps.close();
                rs.close();
                return allCourse;

        }

        // GET COURSE BY ID
        public Course getCourse(int ID) throws Exception {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement("select * from course where course_id = ?;");
                ps.setInt(1, ID);
                ResultSet rs = ps.executeQuery();
                rs.next();
                int courseID = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                int courseLevel = rs.getInt("course_level");
                String courseDescription = rs.getString("course_description");
                User userID = userRepo.getUserbyID(rs.getInt("user_id"));
                Course course = new Course(courseID, courseName, courseLevel, courseDescription, userID);
                con.close();
                ps.close();
                rs.close();
                return course;

        }

        // SEARCH BY NAME COURSE
        public Course SearchCourseByName(String name) throws Exception {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement(
                                "select * from course where course_name=?;");
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                rs.next(); // Di chuyển con trỏ rs đến hàng kết quả đầu tiên trong tập kết quả truy vấn
                int courseID = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                int courseLevel = rs.getInt("course_level");
                String courseDescription = rs.getString("course_description");
                User userID = userRepo.getUserbyID(rs.getInt("user_id"));
                Course course = new Course(courseID, courseName, courseLevel, courseDescription, userID);
                con.close();
                ps.close();
                rs.close();
                return course;
        }
}
