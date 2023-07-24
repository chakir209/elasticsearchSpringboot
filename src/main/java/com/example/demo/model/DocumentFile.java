package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "documents")
@Data
public class DocumentFile {
    @Id
    private String id;
    private String title;
    private String content;
}