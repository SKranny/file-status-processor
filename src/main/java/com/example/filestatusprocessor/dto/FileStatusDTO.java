package com.example.filestatusprocessor.dto;


import com.example.filestatusprocessor.constant.FileProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileStatusDTO {
    private String fileName;
    private FileProcessStatus fileStatus;
}
