package com.example.PJWebTa.Model.Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Files {
    private int idFile;
    private Lesson lesson;
    private String fileName;
    private String filePath; 

    public Files(Lesson lesson, String fileName, String filePath) {
        this.lesson = lesson;
        this.fileName = fileName;
        this.filePath = filePath;
        
    }
    
}
