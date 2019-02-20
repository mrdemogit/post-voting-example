package com.example.postservice.service;

import com.example.postservice.repository.CommentRepository;
import com.example.postservice.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    public CommentRepository commentRepository;

    @Autowired
    public PostService postService;

    @Override
    public Mono<Comment> save(Comment comment) {
        return commentRepository.save(comment)
            .flatMap(savedComment ->
                postService.findByUuid(savedComment.getPostUuid())
                    .flatMap(
                        post -> {
                            List<String> commentIds = post.getComments();
                            commentIds.add(savedComment.getUuid());
                            post.setComments(commentIds);
                            postService.save(post);
                            return Mono.just(savedComment);
                        }
                    )
            );
    }

    @Override
    public Mono<Comment> findByUuid(String uuid) {
        return commentRepository.findByUuid(uuid);
    }

    @Override
    public Mono<Comment> vote(String uuid, boolean voteValue) {
        return commentRepository.findByUuid(uuid)
            .flatMap(comment -> {
                int votes = comment.getVotes();

                if (voteValue) {
                    comment.setVotes(votes + 1);
                } else{
                    comment.setVotes(votes - 1);
                }
                return commentRepository.save(comment);
            });
    }
}
