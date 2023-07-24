package com.example.demo.controller;

import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<String> indexDocument(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file.", HttpStatus.BAD_REQUEST);
        }

        try {
            documentService.indexDocument(file, title);
            return new ResponseEntity<>("Document indexed successfully.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred during document indexing.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}