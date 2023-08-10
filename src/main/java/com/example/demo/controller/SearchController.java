package com.example.demo.controller;

import com.example.demo.model.DocumentFile;
import com.example.demo.model.MultiCriteriaSearchRequest;
import com.example.demo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/documents")
    public ResponseEntity<SearchHits<DocumentFile>> searchDocuments(@RequestBody MultiCriteriaSearchRequest searchRequest) {
        org.springframework.data.elasticsearch.core.SearchHits<DocumentFile> searchHits = searchService.searchDocuments(searchRequest);
        return ResponseEntity.ok(searchHits);
    }
}