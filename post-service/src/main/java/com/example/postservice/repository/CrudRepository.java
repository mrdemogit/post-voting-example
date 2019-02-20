package com.example.postservice.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CrudRepository<T, ID> {

    Mono<T> save(T entity);

    Flux<T> save(List<T> entities);

    Mono<T> findByUuid(ID slotId);
}
