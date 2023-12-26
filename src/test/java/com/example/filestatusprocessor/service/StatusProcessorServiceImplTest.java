package com.example.filestatusprocessor.service;

import com.example.filestatusprocessor.constant.FileProcessStatus;
import com.example.filestatusprocessor.dto.FileStatusDTO;
import com.example.filestatusprocessor.model.FileStatus;
import com.example.filestatusprocessor.repository.FileStatusRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatusProcessorServiceImplTest {
    private FileStatusRepository fileStatusRepository = mock(FileStatusRepository.class);
    private StatusProcessorServiceImpl statusProcessorService = new StatusProcessorServiceImpl(fileStatusRepository, mock(ObjectMapper.class));

    @Test
    void getStatus() {
        FileStatus fileStatus = FileStatus.builder()
                .fileName("file.xls")
                .fileStatus(FileProcessStatus.FIRST_VALIDATION_COMPLETED)
                .build();
        FileStatusDTO fileStatusDTO = FileStatusDTO.builder()
                .fileStatus(fileStatus.getFileStatus())
                .fileName(fileStatus.getFileName())
                .build();

        when(fileStatusRepository.findByFileName("file.xls")).thenReturn(Optional.of(fileStatus));

        assertEquals(fileStatusDTO, statusProcessorService.getStatus("file.xls"));
    }

    @Test
    void postStatus(){
        FileStatusDTO fileStatusDTO = FileStatusDTO
                .builder()
                .fileName("file.xls")
                .fileStatus(FileProcessStatus.FIRST_VALIDATION_COMPLETED)
                .build();
        FileStatus fileStatus = FileStatus.builder()
                .fileStatus(fileStatusDTO.getFileStatus())
                .fileName(fileStatusDTO.getFileName())
                .build();
        assertEquals(fileStatusDTO.getFileStatus(), fileStatus.getFileStatus());
    }

    @Test
    void updateStatus(){
        FileStatusDTO fileStatusDTO = FileStatusDTO
                .builder()
                .fileName("file.xls")
                .fileStatus(FileProcessStatus.SECOND_VALIDATION_COMPLETED)
                .build();
        FileStatus fileStatus = FileStatus.builder()
                .fileStatus(fileStatusDTO.getFileStatus())
                .fileName(fileStatusDTO.getFileName())
                .build();
        assertEquals(fileStatusDTO.getFileStatus(), fileStatus.getFileStatus());
    }

}