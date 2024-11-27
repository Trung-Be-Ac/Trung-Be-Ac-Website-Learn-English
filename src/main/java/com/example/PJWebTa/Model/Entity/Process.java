package com.example.PJWebTa.Model.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Process {
    private int processID;
    private User user;
    private Course course;
    private Double processStatus;
    private String processResult;
}
