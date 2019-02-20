package com.example.postservice.repository;

import com.example.postservice.model.Comment;
import reactor.core.publisher.Flux;

public interface CommentRepository extends CrudRepository<Comment, String> {

    Flux<Comment> findAll();
}
