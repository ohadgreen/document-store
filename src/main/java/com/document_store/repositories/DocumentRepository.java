package com.document_store.repositories;

import com.document_store.model.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long> {
}
