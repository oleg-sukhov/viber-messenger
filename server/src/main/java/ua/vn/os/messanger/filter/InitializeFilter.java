package ua.vn.os.messanger.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class InitializeFilter implements WebFilter {
    private static final String ROOT_PATH = "/";

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, final WebFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final URI requestUri = request.getURI();
        if (ROOT_PATH.equals(requestUri.getPath())) {
            final ServerHttpRequest indexPagePath = request.mutate()
                    .path("/index.html")
                    .build();
            return chain.filter(exchange.mutate().request(indexPagePath).build());
        }
        return chain.filter(exchange);
    }
}
