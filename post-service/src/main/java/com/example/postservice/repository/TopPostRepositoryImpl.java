package com.example.postservice.repository;

import com.example.postservice.model.Post;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.PriorityQueue;

@Repository
public class TopPostRepositoryImpl implements TopPostRepository {

    private final static Integer TOP_SIZE = 10;

    private final PriorityQueue<Post> repo = new PriorityQueue<>(TOP_SIZE, (o1, o2) -> o1.getVotes() - o2.getVotes());

    public boolean updateQueue(Post post) {
        Post peekPost = repo.peek();
        if (repo.size() == TOP_SIZE) {
            if (peekPost != null && post.getVotes() >= peekPost.getVotes()) {
                repo.poll();
            } else {
                return false;
            }
        }
        if (repo.isEmpty() || !repo.contains(post)) {
            return repo.add(post);
        }
        return false;
    }

    @Override
    public Flux<Post> getTop10Posts() {
        return Flux.fromIterable(repo);
    }

}
