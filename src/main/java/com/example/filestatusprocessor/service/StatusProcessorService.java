package com.example.filestatusprocessor.service;

import com.example.filestatusprocessor.dto.FileStatusDTO;

public interface StatusProcessorService {
    void updateStatus(FileStatusDTO fileStatusDTO);
    FileStatusDTO getStatus(byte[] fileBytes);
    void postStatus(FileStatusDTO fileStatusDTO);
}
