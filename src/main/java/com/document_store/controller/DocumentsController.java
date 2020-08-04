package com.document_store.controller;


import com.document_store.model.Document;
import com.document_store.model.DocumentDto;
import com.document_store.services.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = "/documentStore")
public class DocumentsController {

    private final DocumentService documentService;

    public DocumentsController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable Long id) {
        log.debug("id: " + id);
        DocumentDto document = documentService.findById(id);
        if (document != null) {
            return new ResponseEntity<>(document, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<DocumentDto> saveDocument(@RequestBody DocumentDto documentDto) {
        DocumentDto savedDocument = documentService.save(documentDto);
        if (savedDocument != null) {
            log.debug("saved doc: " + savedDocument.toString());
            return new ResponseEntity<>(savedDocument, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping()
    public ResponseEntity<Set<DocumentDto>> getAllDocuments() {
        Set<DocumentDto> allDocuments = documentService.findAll();
        if (!allDocuments.isEmpty()) {
            return new ResponseEntity<>(allDocuments, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
