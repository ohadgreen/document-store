package com.document_store.services;

import com.document_store.model.Document;
import com.document_store.model.DocumentDto;
import com.document_store.model.converters.DocumentToDto;
import com.document_store.model.converters.DtoToDocument;
import com.document_store.repositories.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentToDto documentToDtoConverter;
    private final DtoToDocument dtoToDocumentConverter;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentToDto documentToDtoConverter, DtoToDocument dtoToDocumentConverter) {
        this.documentRepository = documentRepository;
        this.documentToDtoConverter = documentToDtoConverter;
        this.dtoToDocumentConverter = dtoToDocumentConverter;
    }

    @Override
    public Set<DocumentDto> findAll() {
        Set<DocumentDto> documentSet = new HashSet<>();
        Iterable<Document> allDocumentsIterable = documentRepository.findAll();
        allDocumentsIterable.forEach(document -> documentSet.add(documentToDtoConverter.convert(document)));

        return documentSet;
    }

    @Override
    public DocumentDto findById(Long documentId) {
        log.debug(String.valueOf(documentId));
        Document document = documentRepository.findById(documentId).orElse(null);
        return documentToDtoConverter.convert(document);
//        throw new RuntimeException("exception test");
    }

    @Override
    public DocumentDto save(DocumentDto documentDto) {
        Document document = dtoToDocumentConverter.convert(documentDto);
        Document savedDocument = documentRepository.save(document);
        return documentToDtoConverter.convert(savedDocument);
    }
}
