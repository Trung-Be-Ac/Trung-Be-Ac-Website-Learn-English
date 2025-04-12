package com.example.PJWebTa.Controller;

import com.example.PJWebTa.Model.Entity.Files;
import com.example.PJWebTa.Model.Entity.Lesson;
import com.example.PJWebTa.Model.Repository.FileLessonRepo;
import com.example.PJWebTa.Model.Repository.LessonRepo;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FileLessonController {

    @Autowired
    private FileLessonRepo fileLessonRepo;

    @Autowired
    private LessonRepo lessonRepo;

    // Add file
    @GetMapping("/addFilesLesson")
    public String addFileForm(@RequestParam("lessonId") int lessonId, Model model) throws Exception {
        model.addAttribute("lessonId", lessonId);
        return "Lesson/AddFileLesson";
    }
    @PostMapping("/addFile")
    public String addFile(
            @RequestParam("lessonId") int lessonId,
            @RequestParam("fileName") String fileName,
            @RequestParam("fileData") MultipartFile fileData,
            Model model) throws Exception {
        Lesson lesson = lessonRepo.getLessonbyID(lessonId);
        byte[] fileBytes = fileData.getBytes();
        Files file = new Files(lesson, fileName, fileBytes);
        fileLessonRepo.addFile(file);
        return "redirect:/Course";
    }

    // View file and lesson follow
    @GetMapping("/viewFiles")
    public String viewFilesByLesson(@RequestParam("lessonId") int lessonId, Model model) throws Exception {
        List<Files> files = fileLessonRepo.getFilesByLessonId(lessonId);
        ArrayList<Files> allFiles = fileLessonRepo.getAllFiles();
        model.addAttribute("AllFiles", allFiles);
        model.addAttribute("files", files);
        model.addAttribute("lessonId", lessonId);
        return "/Lesson/ViewFilesLesson";
    }

    // Delete File
    @GetMapping("/deleteFile")
    public String deleteFile(@RequestParam("fileId") int fileId, @RequestParam("lessonId") int lessonId)
            throws Exception {
        fileLessonRepo.deleteFileById(fileId);
        return "redirect:/Course";
    }

    // View detail file
    @GetMapping("/viewFileDetail/{fileId}")
    public String viewFileDetailPage(@PathVariable int fileId, Model model) throws Exception {
        Files file = fileLessonRepo.getFileByID(fileId);
        model.addAttribute("filebyID", file);
        return "Lesson/ViewFileDetail";
    }

}
