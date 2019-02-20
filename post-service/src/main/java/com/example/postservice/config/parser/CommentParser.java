package com.example.postservice.config.parser;

import com.example.postservice.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentParser extends AbstractParser<Comment> implements Parser<Comment> {

    @Override
    Class<Comment> getMapperType() {
        return Comment.class;
    }
}
