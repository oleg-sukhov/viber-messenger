package ua.vn.os.messanger.endpoint;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.vn.os.messanger.client.ViberClient;

@Component
public class WebHookHandler {

    private final ViberClient viberClient;

    public WebHookHandler(final ViberClient viberClient) {
        this.viberClient = viberClient;
    }

    public Mono<ServerResponse> sendWebHook(ServerRequest request) {
        viberClient.sendWebHook();
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just("WebHook response test!!!"), String.class);
    }

    public Mono<ServerResponse> home(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(Flux.just("App works!!!"), String.class);
    }
}
