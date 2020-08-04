package com.document_store.model.converters;

import com.document_store.model.Document;
import com.document_store.model.DocumentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToDocument implements Converter<DocumentDto, Document> {
    @Override
    public Document convert(DocumentDto documentDto) {
        return Document.builder()
                .name(documentDto.getName())
                .profession(documentDto.getProfession())
                .build();
    }
}
