package com.example.filestatusprocessor.service;

import com.example.filestatusprocessor.dto.FileStatusDTO;
import com.example.filestatusprocessor.model.FileStatus;
import com.example.filestatusprocessor.repository.FileStatusRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusProcessorServiceImpl implements StatusProcessorService {
    private final FileStatusRepository fileStatusRepository;
    private final ObjectMapper objectMapper;
    @KafkaListener(topics = "status-topic", groupId = "file-processor-id")
    public void getFileStatus(String message) throws JsonProcessingException {
        updateStatus(objectMapper.readValue(message, FileStatusDTO.class));
    }

    @Override
    public void updateStatus(FileStatusDTO fileStatusDTO) {
        FileStatus fileStatus = FileStatus.builder()
                .fileStatus(fileStatusDTO.getFileStatus())
                .fileName(fileStatusDTO.getFileName())
                .build();
        fileStatusRepository.save(fileStatus);
    }

    @Override
    public FileStatusDTO getStatus(byte[] fileBytes) {
        FileStatus fileStatus = fileStatusRepository.findByFileBytes(fileBytes)
                .orElseThrow(() -> new RuntimeException("File with name:{fileName} not found")); // byte
        return FileStatusDTO.builder()
                .fileName(fileStatus.getFileName())
                .fileStatus(fileStatus.getFileStatus())
                .build();
    }

    @Override
    public void postStatus(FileStatusDTO fileStatusDTO) {
        FileStatus fileStatus = FileStatus.builder()
                .fileStatus(fileStatusDTO.getFileStatus())
                .fileName(fileStatusDTO.getFileName())
                .build();
        fileStatusRepository.save(fileStatus);
    }
}
