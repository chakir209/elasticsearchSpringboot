package com.example.demo.repository;

import com.example.demo.model.DocumentFile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DocumentRepository extends ElasticsearchRepository<DocumentFile, String> {

}