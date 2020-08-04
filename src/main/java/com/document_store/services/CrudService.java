package com.document_store.services;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.rmi.ServerException;
import java.util.Set;

public interface CrudService <T, ID> {
    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 2000))
    Set<T> findAll();

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 2000))
    T findById(ID id);

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 2000))
    T save(T object);
}
