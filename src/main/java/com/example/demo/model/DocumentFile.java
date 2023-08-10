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

    private Boolean folder = false;
    //private BigDecimal size = BigDecimal.ZERO;

    private String description;
}