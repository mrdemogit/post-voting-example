package com.example.postservice.service;

import com.example.postservice.model.Post;
import com.example.postservice.repository.CommentRepository;
import com.example.postservice.repository.PostRepository;
import com.example.postservice.repository.TopPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopPostRepository topPostRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Mono<Post> save(Post post) {
        topPostRepository.updateQueue(post);
        return postRepository.save(post);
    }

    @Override
    public Mono<Post> findByUuid(String uuid) {
        return postRepository.findByUuid(uuid);
    }

    @Override
    public Mono<Post> vote(String uuid, boolean voteValue) {
        return postRepository
            .findByUuid(uuid)
            .flatMap(post -> {
                int votes = post.getVotes();
                if(voteValue){
                    post.setVotes(votes + 1);
                }else {
                    post.setVotes(votes - 1);
                }
                return postRepository.save(post);
            }).map(
                post -> {
                    topPostRepository.updateQueue(post);
                    return post;
                }
            );
    }

    @Override
    public Flux<Post> getTop10ByVotes() {
        return topPostRepository
            .getTop10Posts()
            .sort((o1, o2) -> o2.getVotes() - o1.getVotes());
    }
}
