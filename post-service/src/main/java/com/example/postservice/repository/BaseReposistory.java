package com.example.postservice.repository;

import com.example.postservice.model.BaseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

abstract class BaseReposistory<ENTITY extends BaseEntity, ID> {

    private final HashMap<ID, ENTITY> map = new HashMap<>();

    @SuppressWarnings("unchecked")
    public Mono<ENTITY> save(ENTITY entity) {
        map.put((ID) entity.getUuid(), entity);
        return Mono.just(entity);
    }

    @SuppressWarnings("unchecked")
    public Flux<ENTITY> save(List<ENTITY> entities) {
        for (ENTITY entity : entities) {
            map.put((ID) entity.getUuid(), entity);
        }
        return Flux.fromIterable(entities);
    }

    public Mono<ENTITY> findByUuid(ID uuid){
        return Mono.justOrEmpty(map.get(uuid));
    }

    public Flux<ENTITY> findAll(){
        return Flux.fromIterable(map.values());
    }

    public HashMap<ID, ENTITY> getRepo() {
        return map;
    }

}
