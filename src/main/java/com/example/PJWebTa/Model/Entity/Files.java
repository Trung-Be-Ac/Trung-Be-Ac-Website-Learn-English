package com.example.PJWebTa.Model.Entity;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Files {  
    private int idFile;
    private String fileName;
    private byte[] fileData;


}
