package com.example.postservice.config.parser;

import com.example.postservice.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostParser extends AbstractParser<Post> implements Parser<Post> {

    @Override
    Class<Post> getMapperType() {
        return Post.class;
    }
}
