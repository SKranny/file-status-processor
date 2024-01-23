package com.example.filestatusprocessor.controller;

import com.example.filestatusprocessor.dto.FileStatusDTO;
import com.example.filestatusprocessor.service.StatusProcessorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
@AllArgsConstructor
public class StatusController {
    private final StatusProcessorService statusProcessorService;

    @GetMapping
    public FileStatusDTO checkFileStatus(FileStatusDTO fileStatusDTO){
        return statusProcessorService.getStatus(fileStatusDTO.getFileBytes());
    }
    @PostMapping
    public void postStatus(FileStatusDTO fileStatusDTO){
        statusProcessorService.postStatus(fileStatusDTO);
    }

    @PutMapping
    public void updateStatus(FileStatusDTO fileStatusDTO){
        statusProcessorService.updateStatus(fileStatusDTO);
    }


}
