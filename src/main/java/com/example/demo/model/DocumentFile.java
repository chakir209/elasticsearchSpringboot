package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Document(indexName = "documents")
@Data
public class DocumentFile {
    @Id
    private String id;
    private String title;
    private String content;
    private String referenceGed;
    private LocalDateTime uploadDate ;
    private LocalDateTime dateLastUpdate ;
}