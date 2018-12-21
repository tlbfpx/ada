package com.test.gateway.filter;

import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

/**
 * @author ezbuy on ${date}
 */
public interface BodyHandlerFunction

        extends BiFunction<ServerHttpResponse, Publisher<? extends DataBuffer>, Mono<Void>> {


}