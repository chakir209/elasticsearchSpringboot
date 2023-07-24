package com.example.demo.service;

import com.example.demo.repository.DocumentRepository;

import com.example.demo.model.DocumentFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public String extractTextFromPDF(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            Parser parser = new AutoDetectParser();
            ParseContext parseContext = new ParseContext();
            parser.parse(inputStream, handler, metadata, parseContext);
            return handler.toString();
        } catch (Exception e) {
            // Handle extraction errors
            e.printStackTrace();
            return null;
        }
    }
    public void indexDocument(MultipartFile file, String title) throws IOException {
        String content = extractTextFromPDF(file);

        if (content != null) {
            DocumentFile document = new DocumentFile();
            document.setTitle(title);
            document.setContent(content);

            documentRepository.save(document);
        }
    }
}