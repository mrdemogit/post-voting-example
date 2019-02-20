package com.example.postservice.service;

import com.example.postservice.model.Comment;
import reactor.core.publisher.Mono;

public interface CommentService {

    Mono<Comment> save(Comment comment);

    Mono<Comment> findByUuid(String uuid);

    Mono<Comment> vote(String uuid, boolean voteValue);
}
