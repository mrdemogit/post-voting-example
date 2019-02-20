package com.example.postservice.service;

import com.example.postservice.model.Post;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {

    Mono<Post> save(Post post);

    Mono<Post> findByUuid(String uuid);

    Mono<Post> vote(String uuid, boolean voteValue);

    Flux<Post> getTop10ByVotes();
}
