package com.example.demo.controller;

import com.example.demo.model.DocumentFile;
import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private  DocumentService documentService;




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