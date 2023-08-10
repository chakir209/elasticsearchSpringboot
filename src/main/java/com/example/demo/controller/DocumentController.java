package com.example.demo.controller;

import com.example.demo.model.DocumentFile;
import com.example.demo.model.MultiCriteriaSearchRequest;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.service.DocumentService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.erhlc.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQuery;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.elasticsearch.core.query.Criteria;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private  DocumentService documentService;
    @Autowired
    private DocumentRepository documentRepository;

    @PostMapping(value = "/indexer")
    public ResponseEntity<String> indexDocument( @RequestBody DocumentFile document) {
        if (document==null ) {
            return new ResponseEntity<>("Please select a file.", HttpStatus.BAD_REQUEST);
        }

        try {
            documentService.indexDocument(document);
            return new ResponseEntity<>("Document indexed successfully.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred during document indexing.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}






