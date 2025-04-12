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
    private byte[] fileData;

    public Files(Lesson lesson, String fileName, byte[] fileData) {
        this.lesson = lesson;
        this.fileName = fileName;
        this.fileData = fileData;
    }
}
