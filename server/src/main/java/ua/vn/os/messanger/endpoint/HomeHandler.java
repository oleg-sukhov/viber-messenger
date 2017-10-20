package ua.vn.os.messanger.endpoint;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HomeHandler {

    public Mono<ServerResponse> home(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(Flux.empty(), String.class);
    }
}
