package com.example.postservice.repository;

import com.example.postservice.model.Post;
import reactor.core.publisher.Flux;

public interface TopPostRepository {

    boolean updateQueue(Post post);

    Flux<Post> getTop10Posts();
}
