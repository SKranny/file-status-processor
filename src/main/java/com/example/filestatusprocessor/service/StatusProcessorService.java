package com.example.filestatusprocessor.service;

import com.example.filestatusprocessor.dto.FileStatusDTO;

public interface StatusProcessorService {
    void updateStatus(FileStatusDTO fileStatusDTO);
    FileStatusDTO getStatus(String fileName);
    void postStatus(FileStatusDTO fileStatusDTO);
}
