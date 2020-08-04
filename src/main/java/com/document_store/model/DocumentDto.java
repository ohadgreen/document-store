package com.document_store.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DocumentDto {
    private String name;
    private String profession;
}
