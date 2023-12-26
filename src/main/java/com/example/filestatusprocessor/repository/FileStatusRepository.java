package com.example.filestatusprocessor.repository;

import com.example.filestatusprocessor.model.FileStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileStatusRepository extends MongoRepository<FileStatus, String> {
    Optional<FileStatus> findByFileName(String fileName);
}
