package com.test.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author ezbuy on ${date}
 */
@Component
@Slf4j
public class LanguageFilter implements GlobalFilter, Ordered {




    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String lang = request.getHeaders().getFirst("lang");

        String origin = exchange.getRequest().getURI().toString();

        String changeUrl = origin + "?lang=" + lang;
        URI url=null;
        try {
            url = new URI(changeUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ServerHttpRequest host = exchange.getRequest().mutate().uri(url).build();
        //将现在的request 变成 change对象
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build);

    }

    @Override
    public int getOrder() {
        return 0;
    }



}
