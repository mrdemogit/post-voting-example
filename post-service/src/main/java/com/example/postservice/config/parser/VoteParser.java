package com.example.postservice.config.parser;

import com.example.postservice.model.Vote;
import org.springframework.stereotype.Component;

@Component
public class VoteParser extends AbstractParser<Vote> implements Parser<Vote> {

    @Override
    Class<Vote> getMapperType() {
        return Vote.class;
    }

}
