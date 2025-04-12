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
                "INSERT INTO files (lesson_id, file_name, file_data) VALUES (?, ?, ?)");
        ps.setInt(1, file.getLesson().getLessonID());
        ps.setString(2, file.getFileName());
        ps.setBytes(3, file.getFileData());
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
            byte[] fileData = rs.getBytes("file_data");
            Files file = new Files(fileId, lesson, fileName, fileData);
            fileList.add(file);
        }
        rs.close();
        ps.close();
        con.close();
        return fileList;
    }

    // Get all Lesson
    public ArrayList<Files> getAllFiles() throws Exception {
        ArrayList<Files> allFiles = new ArrayList<>();
        Class.forName(BaseConnection.nameClass);
        Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                BaseConnection.password);
        PreparedStatement ps = con.prepareStatement(
                "Select * from files");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int filesID = rs.getInt("id");
            Lesson lessonID = lessonRepo.getLessonbyID(rs.getInt("lesson_id"));
            String filesName = rs.getString("file_name");
            byte[] filesData = rs.getBytes("file_data");
            Files files = new Files(filesID, lessonID, filesName, filesData);
            allFiles.add(files);
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
        PreparedStatement ps = con.prepareStatement("select * from PRODUCT where IDProduct = ?");
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery();
        Files files = null;
        if (rs.next()) {
            int filesID = rs.getInt("id");
            Lesson lessonID = lessonRepo.getLessonbyID(rs.getInt("lesson_id"));
            String filesName = rs.getString("file_name");
            byte[] filesData = rs.getBytes("file_data");
            files = new Files(filesID, lessonID, filesName, filesData); 
        }
        con.close();
        ps.close();
        rs.close();
        return files;
    }
}
