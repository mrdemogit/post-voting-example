package com.example.postservice.config.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractParser<ENTITY> implements Parser<ENTITY> {


    abstract Class<ENTITY> getMapperType();

    @Autowired
    ObjectMapper objectMapper;

    public Flux<ENTITY> parse(InputStream inputStream) {
        return Flux.create(
            sink -> pullOrComplete(inputStream, sink)
        );
    }


    private void pullOrComplete(InputStream inputStream, FluxSink<ENTITY> sink) {
        try {
            final JsonParser parser = new JsonFactory().createParser(inputStream);
            parser.nextToken();
            parser.nextToken();

            while (parser.nextToken() != null) {

                if (parser.getCurrentToken() != JsonToken.END_OBJECT && parser.getCurrentToken() != JsonToken.END_ARRAY) {
                    final ENTITY object = objectMapper.readValue(parser, getMapperType());
                    sink.next(object);
                } else {
                    sink.complete();
                }
            }

        } catch (IOException e) {
            sink.error(e);
        }
    }
}
