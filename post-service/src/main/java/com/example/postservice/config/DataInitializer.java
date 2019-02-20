package com.example.postservice.config;

import com.example.postservice.config.parser.CommentParser;
import com.example.postservice.config.parser.VoteParser;
import com.example.postservice.model.Comment;
import com.example.postservice.model.Post;
import com.example.postservice.model.Vote;
import com.example.postservice.service.CommentService;
import com.example.postservice.service.PostService;
import com.example.postservice.config.parser.PostParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.InputStream;
import java.util.Date;

@Component
public class DataInitializer {

    Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostParser postParser;

    @Autowired
    private CommentParser commentParser;

    @Autowired
    private VoteParser voteParser;

    public void init() {
        InputStream postsInputStream = this.getClass().getClassLoader().getResourceAsStream("posts.json");
        InputStream commentsInputStream = this.getClass().getClassLoader().getResourceAsStream("comments.json");
        InputStream voteInputStream = this.getClass().getClassLoader().getResourceAsStream("votes.json");

        Flux<Post> posts = postParser
            .parse(postsInputStream)
            .flatMap(
                post -> postService.save(post)
            );

        Flux<Comment> comments = commentParser
            .parse(commentsInputStream)
            .flatMap(
                comment -> commentService.save(comment)
            );

        Flux<Vote> votes = voteParser
            .parse(voteInputStream);

        long start = new Date().getTime();
        logger.info("Initializing Posts...");
        posts.subscribe();
        logger.info("Initializing Comments...");
        comments.subscribe();
        logger.info("Initializing Votes...");
        votes.subscribe(
            vote -> Flux.merge(
                postService.vote(vote.getUuid(), vote.isUpvote()),
                commentService.vote(vote.getUuid(), vote.isUpvote())
            ).subscribe());
        logger.info("Initializing done");
    }


}
