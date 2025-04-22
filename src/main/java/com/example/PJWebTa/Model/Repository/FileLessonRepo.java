package com.example.PJWebTa.Model.Repository;

import com.example.PJWebTa.Model.Entity.Files;
import com.example.PJWebTa.Model.Entity.Lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class FileLessonRepo {
    @Autowired
    LessonRepo lessonRepo;

    // Add
    public void addFile(Files file) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO files (lesson_id, file_name, file_path) VALUES (?, ?, ?)");
        ps.setInt(1, file.getLesson().getLessonID());
        ps.setString(2, file.getFileName());
        ps.setString(3, file.getFilePath()); // Lưu đường dẫn
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    // Delete
    public void deleteFileById(int id) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("DELETE FROM files WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    // Get by lesson ID
    public ArrayList<Files> getFilesByLessonId(int lessonId) throws Exception {
        ArrayList<Files> fileList = new ArrayList<>();
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("SELECT * FROM files WHERE lesson_id = ?");
        ps.setInt(1, lessonId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int fileId = rs.getInt("id");
            Lesson lesson = lessonRepo.getLessonbyID(lessonId);
            String fileName = rs.getString("file_name");
            String filePath = rs.getString("file_path");
            Files file = new Files(fileId, lesson, fileName, filePath);
            fileList.add(file);
        }
        rs.close();
        ps.close();
        con.close();
        return fileList;
    }

    // Get all files
    public ArrayList<Files> getAllFiles() throws Exception {
        ArrayList<Files> allFiles = new ArrayList<>();
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("SELECT * FROM files");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int filesID = rs.getInt("id");
            Lesson lesson = lessonRepo.getLessonbyID(rs.getInt("lesson_id"));
            String filesName = rs.getString("file_name");
            String filePath = rs.getString("file_path");
            Files file = new Files(filesID, lesson, filesName, filePath);
            allFiles.add(file);
        }
        con.close();
        ps.close();
        rs.close();
        return allFiles;
    }

    // Search by ID
    public Files getFileByID(int ID) throws Exception {
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement("SELECT * FROM files WHERE id = ?");
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        Files file = null;
        if (rs.next()) {
            int fileId = rs.getInt("id");
            Lesson lesson = lessonRepo.getLessonbyID(rs.getInt("lesson_id"));
            String fileName = rs.getString("file_name");
            String filePath = rs.getString("file_path");
            file = new Files(fileId, lesson, fileName, filePath);
        }
        con.close();
        ps.close();
        rs.close();
        return file;
    }
}
