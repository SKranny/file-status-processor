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
        byte[] bytes = new byte[]{1,2,3};
        FileStatus fileStatus = FileStatus.builder()
                .fileName("file.xls")
                .fileBytes(bytes)
                .fileStatus(FileProcessStatus.FIRST_VALIDATION_COMPLETED)
                .build();
        FileStatusDTO fileStatusDTO = FileStatusDTO.builder()
                .fileStatus(fileStatus.getFileStatus())
                .fileBytes(fileStatus.getFileBytes())
                .fileName(fileStatus.getFileName())
                .build();

        when(fileStatusRepository.findByFileBytes(bytes)).thenReturn(Optional.of(fileStatus));

        assertEquals(fileStatusDTO, statusProcessorService.getStatus(fileStatusDTO.getFileBytes()));
    }

    @Test
    void postStatus(){
        byte[] bytes = new byte[]{1,2,3};
        FileStatusDTO fileStatusDTO = FileStatusDTO
                .builder()
                .fileBytes(bytes)
                .fileName("file.xls")
                .fileStatus(FileProcessStatus.FIRST_VALIDATION_COMPLETED)
                .build();
        FileStatus fileStatus = FileStatus.builder()
                .fileStatus(fileStatusDTO.getFileStatus())
                .fileBytes(fileStatusDTO.getFileBytes())
                .fileName(fileStatusDTO.getFileName())
                .build();
        assertEquals(fileStatusDTO.getFileStatus(), fileStatus.getFileStatus());
    }

    @Test
    void updateStatus(){
        byte[] bytes = new byte[]{1,2,3};
        FileStatusDTO fileStatusDTO = FileStatusDTO
                .builder()
                .fileBytes(bytes)
                .fileName("file.xls")
                .fileStatus(FileProcessStatus.SECOND_VALIDATION_COMPLETED)
                .build();
        FileStatus fileStatus = FileStatus.builder()
                .fileStatus(fileStatusDTO.getFileStatus())
                .fileBytes(fileStatusDTO.getFileBytes())
                .fileName(fileStatusDTO.getFileName())
                .build();
        assertEquals(fileStatusDTO.getFileStatus(), fileStatus.getFileStatus());
    }

}