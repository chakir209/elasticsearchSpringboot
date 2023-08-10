package com.example.demo.repository;

import com.example.demo.model.DocumentFile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DocumentRepository extends ElasticsearchRepository<DocumentFile, String> {
    List<DocumentFile> findByDescription(String description);
    List<DocumentFile> findByReferenceGed(String reference);
    List<DocumentFile> findByUploadDate(LocalDateTime uploadDate);
    List<DocumentFile> findByContent(String content);
    List<DocumentFile> findByDateLastUpdate(LocalDateTime uploadDate);
}