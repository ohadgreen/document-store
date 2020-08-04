package com.document_store.services;

import com.document_store.controller.DocumentsController;
import com.document_store.model.Document;
import com.document_store.model.DocumentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.retry.annotation.EnableRetry;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@EnableRetry
@SpringBootTest
class DocumentServiceImplRetryTest {

    @Autowired
    DocumentsController documentsController;
    @MockBean
    DocumentService documentService;

    @Test
    void getByIdRetryTest_shouldSucceed() {
        DocumentDto testDocument = DocumentDto.builder().name("test").build();

        when(documentService.findById(1L))
                .thenThrow(new RuntimeException("Ex 1"))
                .thenThrow(new RuntimeException("Ex 2"))
                .thenReturn(testDocument);

        documentsController.getDocumentById(1L);
        verify(documentService, times(3));
    }
}