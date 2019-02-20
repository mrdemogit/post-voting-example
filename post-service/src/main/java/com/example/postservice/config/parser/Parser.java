package com.example.postservice.config.parser;

import reactor.core.publisher.Flux;

import java.io.InputStream;

interface Parser<ENTITY> {

    Flux<ENTITY> parse(InputStream inputStream);
}
