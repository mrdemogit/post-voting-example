package com.example.postservice.controller;

import com.example.postservice.dto.CommentDto;
import com.example.postservice.dto.PostDto;
import com.example.postservice.model.Post;
import com.example.postservice.service.CommentService;
import com.example.postservice.service.PostService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @GetMapping("/top")
    public Flux<PostDto> getTop10Posts() {
        return postService
            .getTop10ByVotes()
            .flatMap(mapToDto);
    }

    @PostMapping("/upvote/{uuid}")
    public Flux<PostDto> upvote(@PathVariable String uuid) {
        Flux.merge(
            postService.vote(uuid, true),
            commentService.vote(uuid, true)
        ).subscribe();
        return postService.getTop10ByVotes().flatMap(mapToDto);
    }

    @PostMapping("/downvote/{uuid}")
    public Flux<PostDto> downvote(@PathVariable String uuid) {
        Flux.merge(
            postService.vote(uuid, false),
            commentService.vote(uuid, false)
        ).subscribe();
        return postService.getTop10ByVotes().flatMap(mapToDto);
    }

    private final Function<Post, Publisher<PostDto>> mapToDto = post -> Flux.fromIterable(post.getComments())
        .flatMap(commentUuid -> commentService.findByUuid(commentUuid))
        .map(comment -> new CommentDto(comment.getUuid(), comment.getPostUuid(), comment.getAuthor(), comment.getAuthor(), comment.getVotes()))
        .sort((o1, o2) -> o2.getVotes() - o1.getVotes())
        .take(3)
        .collect(Collectors.toList())
        .map(
            comments -> new PostDto(post.getUuid(), post.getAuthor(), post.getContent(), post.getVotes(), comments)
        );
}
