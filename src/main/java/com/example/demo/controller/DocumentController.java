package com.example.demo.controller;

import com.example.demo.model.DocumentFile;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/search/content")
    public List<DocumentFile> searchDocumentsByContent(@RequestParam String content) {
        return documentRepository.findByContent(content);
    }

    @GetMapping("/search/reference")
    public List<DocumentFile> searchDocumentsByReferenceGed(@RequestParam String query) {
        return documentRepository.findByReferenceGed(query);
    }
    @GetMapping("/search/reference/UploadDate")
    public List<DocumentFile> searchDocumentsByUploadDate(@RequestParam LocalDateTime dateTime) {
        return documentRepository.findByUploadDate(dateTime);
    }
    @GetMapping("/search/reference/description")
    public List<DocumentFile> searchDocumentsByDescription(@RequestParam String desc) {
        return documentRepository.findByDescription(desc);
    }
}