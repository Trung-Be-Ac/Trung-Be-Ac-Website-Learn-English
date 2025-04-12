package com.example.PJWebTa.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.stream.Collectors;

@Controller
public class FilePDFController {

    private final Path uploadDir = Paths.get("uploads");
    private static final String UPLOAD_DIR = "C:/LapTrinh/WebTiengAnh/PJWebTa/uploads/";

    public FilePDFController() throws IOException {
        Files.createDirectories(uploadDir);
    }

    @GetMapping("/filePDF")
    public String listUploadedFiles(Model model) throws IOException {
        var files = Files.list(uploadDir)
                .filter(f -> f.toString().endsWith(".pdf"))
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());
        model.addAttribute("files", files);
        return "mainFilePDF";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getOriginalFilename().endsWith(".pdf")) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Files.copy(file.getInputStream(), uploadDir.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        }
        return "redirect:/filePDF";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException {
        Path filePath = Paths.get("C:/LapTrinh/WebTiengAnh/PJWebTa/uploads/").resolve(filename);

        // Kiểm tra tệp có tồn tại không
        if (!Files.exists(filePath)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Resource resource = new FileSystemResource(filePath);
        if (!resource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @GetMapping("/pdf-viewer")
    public String viewPdf(@RequestParam String file, Model model) {
        model.addAttribute("file", file);
        return "viewFilePDF";
    }

    @GetMapping("/UploadAndView")
    public String viewPdf(Model model) {
        return "UploadAndView";

    }
}
