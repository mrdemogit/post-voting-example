package com.example.postservice.repository;

import com.example.postservice.model.Post;
import reactor.core.publisher.Flux;

public interface PostRepository extends CrudRepository<Post, String>  {

    Flux<Post> findAll();

}
