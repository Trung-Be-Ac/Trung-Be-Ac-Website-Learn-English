package com.example.PJWebTa.Controller;

import com.example.PJWebTa.Model.Entity.Files;
import com.example.PJWebTa.Model.Entity.Lesson;
import com.example.PJWebTa.Model.Repository.FileLessonRepo;
import com.example.PJWebTa.Model.Repository.LessonRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileLessonController {

    @Autowired
    private FileLessonRepo fileLessonRepo;

    @Autowired
    private LessonRepo lessonRepo;

    // Đường dẫn thư mục lưu file (không dùng getRealPath nữa)
    private final String uploadFolder = "src/main/resources/FilesPDF/";
    private final String uploadFolder2= "target/classes/static/FilesPDF/";

    // Hiện form upload
    @GetMapping("/addFilesLesson")
    public String addFileForm(@RequestParam("lessonId") int lessonId, Model model) {
        model.addAttribute("lessonId", lessonId);
        return "Lesson/AddFileLesson";
    }

    @PostMapping("/addFile")
    public String addFile(
            @RequestParam("lessonId") int lessonId,
            @RequestParam("fileName") String fileName,
            @RequestParam("fileData") MultipartFile fileData) throws Exception {

        Lesson lesson = lessonRepo.getLessonbyID(lessonId);

        String uploadDirPath = System.getProperty("user.dir") + "/src/main/resources/static/FilesPDF";
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = fileData.getOriginalFilename();
        String savedFileName = System.currentTimeMillis() + "_" + originalFilename;
        String filePath = uploadDirPath + File.separator + savedFileName;

        fileData.transferTo(new File(filePath));

        String relativePath = "FilesPDF/" + savedFileName;
        Files file = new Files(lesson, fileName, relativePath);
        fileLessonRepo.addFile(file);

        return "redirect:/Course";
    }

    // Xem file của bài học
    @GetMapping("/viewFiles")
    public String viewFilesByLesson(@RequestParam("lessonId") int lessonId, Model model) throws Exception {
        List<Files> files = fileLessonRepo.getFilesByLessonId(lessonId);
        ArrayList<Files> allFiles = fileLessonRepo.getAllFiles();
        model.addAttribute("AllFiles", allFiles);
        model.addAttribute("files", files);
        model.addAttribute("lessonId", lessonId);
        return "/Lesson/ViewFilesLesson";
    }

    // Xoá file
    @GetMapping("/deleteFile")
    public String deleteFile(@RequestParam("fileId") int fileId, @RequestParam("lessonId") int lessonId)
            throws Exception {
        fileLessonRepo.deleteFileById(fileId);
        return "redirect:/Course";
    }

    // Xem chi tiết file
    @GetMapping("/viewFileDetail/{fileId}")
    public String viewFileDetailPage(@PathVariable int fileId, Model model) throws Exception {
        Files file = fileLessonRepo.getFileByID(fileId);
    
        if (file == null) {
            return "error";
        }
    
        model.addAttribute("filebyID", file);
    
        // Lấy tên file cuối cùng từ đường dẫn tương đối
        String fullPath = file.getFilePath(); // Ví dụ: FilesPDF/1745042588488_BaiTapTH.pdf
        String fileName = fullPath.substring(fullPath.lastIndexOf("/") + 1); // chỉ lấy 1745042588488_BaiTapTH.pdf
    
        String pdfUrl = "/FilesPDF/" + fileName;
        model.addAttribute("pdfUrl", pdfUrl);
    
        return "Lesson/ViewFileDetail";
    }
    @GetMapping("/checkViewFile")
    public String checkViewFile(Model model) {
        // Truyền đường dẫn của file PDF vào Model
        // model.addAttribute("pdfFilePath", "/view-pdf");  // Sử dụng "/view-pdf" để hiển thị trong iframe
        return "Lesson/ViewFileDetail";  // Trả về view HTML
    }
    @GetMapping("/view-pdf")
    public ResponseEntity<Resource> getPdf() {
        Resource pdfFile = new ClassPathResource("static/FilesPDF/BaiTapTH.pdf");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=BaiTapTH.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfFile);
    }
    
    
    


    // Trả về file PDF
    @GetMapping("/FilesPDF/{fileName:.+}")
    public void servePdf(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        String filePath = uploadFolder2 + fileName;
        File file = new File(filePath);
    
        if (file.exists()) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
            response.setHeader("Access-Control-Allow-Origin", "*"); // Quan trọng nếu bạn đang fetch bằng JS
            java.nio.file.Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } else {
            response.sendError(404, "File not found");
        }
    }
    
}
