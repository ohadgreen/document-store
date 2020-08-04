package com.document_store.services;


import com.document_store.model.Document;
import com.document_store.model.DocumentDto;
import com.document_store.model.converters.DocumentToDto;
import com.document_store.model.converters.DtoToDocument;
import com.document_store.repositories.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;

import java.rmi.ServerException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentServiceImplTest {

    @Mock
    DocumentRepository documentRepository;
    @Mock
    DocumentToDto documentToDtoConverter;
    @Mock
    DtoToDocument dtoToDocumentConverter;
    @InjectMocks
    DocumentServiceImpl documentService;

    private Document testDocument = Document.builder().id(1L).name("doc").profession("test").build();
    private DocumentDto testDto = DocumentDto.builder().name("doc").profession("test").build();

    @Test
    void findByIdNotExist_ShouldReturnNull() {
        when(documentRepository.findById(any())).thenReturn(Optional.empty());
        when(documentToDtoConverter.convert(any())).thenReturn(null);
        DocumentDto document = documentService.findById(1L);
        assertNull(document);
    }

    @Test
    void findByIdTest_ShouldReturnDto() {
        when(documentRepository.findById(any())).thenReturn(java.util.Optional.of(testDocument));
        when(documentToDtoConverter.convert(any())).thenReturn(testDto);
        DocumentDto documentDto = documentService.findById(1L);
        assertEquals(testDocument.getName(), testDto.getName());
    }

    @Test
    void saveDocument_ShouldReturnDoc() {
        DocumentDto newDoc = DocumentDto.builder().name("new doc").profession("comedian").build();
        when(documentRepository.save(any())).thenReturn(testDocument);
        when(dtoToDocumentConverter.convert(any())).thenReturn(testDocument);
        when(documentToDtoConverter.convert(any())).thenReturn(testDto);
        DocumentDto documentDto = documentService.save(newDoc);

        assertNotNull(documentDto);
    }
}