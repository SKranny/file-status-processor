package com.example.filestatusprocessor.model;

import com.example.filestatusprocessor.constant.FileProcessStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileStatus {
    @Id
    private String id;
    private String fileName;
    private byte[] fileBytes;
    private FileProcessStatus fileStatus;
}
