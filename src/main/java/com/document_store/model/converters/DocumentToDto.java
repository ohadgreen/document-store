package com.document_store.model.converters;

import com.document_store.model.Document;
import com.document_store.model.DocumentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DocumentToDto implements Converter<Document, DocumentDto> {
    @Override
    public DocumentDto convert(Document document) {
        return DocumentDto.builder()
                .name(document.getName())
                .profession(document.getProfession())
                .build();
    }
}
