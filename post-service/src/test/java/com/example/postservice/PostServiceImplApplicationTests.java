package com.example.postservice;

import com.example.postservice.config.DataInitializer;
import com.example.postservice.dto.PostDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PostServiceApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostServiceImplApplicationTests {

    private WebTestClient webClient;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private DataInitializer dataInitializer;

    @Before
    public void setup(){
        webClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void shoudReturnEmptyArray(){
        webClient.get().uri("/api/top").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(PostDto.class)
            .hasSize(0);
    }

    @Test
    public void shouldGetTop10PostsWithMax3CommentsOrderedAll() {
        dataInitializer.init();

        Mono.delay(Duration.ofMillis(10000)).subscribe();
        webClient.get().uri("/api/top").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(PostDto[].class)
            .consumeWith(consumer -> {
                List<PostDto> posts = Arrays.asList(consumer.getResponseBody());
                Assert.assertEquals("35174ed2-488e-49b7-aa6e-fd5ec1465f6a", posts.get(0).getUuid());
                Assert.assertEquals(19, posts.get(0).getVotes());
                Assert.assertEquals(1, posts.get(0).getComments().size());
                Assert.assertEquals(-6, posts.get(0).getComments().get(0).getVotes());

                Assert.assertEquals("20ad64f7-02a5-44f7-aaf4-315164f41ead", posts.get(9).getUuid());
                Assert.assertEquals(3, posts.get(9).getComments().size());
                Assert.assertEquals(0, posts.get(9).getComments().get(0).getVotes());
            });
    }

    /**
     *  TODO same style upvote, downvote for comments and posts
     *  ...
     *  + unit tests for each layer
     */

}

