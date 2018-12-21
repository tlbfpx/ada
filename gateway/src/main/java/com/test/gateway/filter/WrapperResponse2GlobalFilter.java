package com.test.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author ezbuy on ${date}
 */
@Component
@Slf4j
public class WrapperResponse2GlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
        System.out.println("Test1GlobalFilter进入此方法");
        //构建响应拦截处理器
        BodyHandlerFunction bodyHandler = (resp, body) -> Flux.from(body)
                .map(dataBuffer -> {
                    //dataBuffer 转换为String
                    byte[] content = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(content);
                    String reqBody =new String(content);
                    System.out.println(reqBody);
                    return reqBody;
                }).flatMap(orgBody -> {
                    String rbody = orgBody + "new1234";
                    HttpHeaders headers = resp.getHeaders();
                    headers.setContentLength(rbody.length());
                    return resp.writeWith(Flux.just(rbody)
                            .map(bx -> resp.bufferFactory()
                                    .wrap(bx.getBytes())));
                }).then();

        //构建响应包装类
        BodyHandlerServerHttpResponseDecorator responseDecorator = new BodyHandlerServerHttpResponseDecorator(
                bodyHandler, exchange.getResponse());
        return chain
                .filter(exchange.mutate().response(responseDecorator).build());
    }

    @Override
    public int getOrder() {
        //WRITE_RESPONSE_FILTER 之前执行
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 2;
    }


}
