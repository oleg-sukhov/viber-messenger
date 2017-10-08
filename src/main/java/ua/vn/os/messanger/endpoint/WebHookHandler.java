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

    public Mono<ServerResponse> sendStartConversationWebHook(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(viberClient.sendStartConversationWebHook(), String.class);
    }

    public Mono<ServerResponse> sendEndConversationWebHook(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(viberClient.sendEndConversationWebHook(), String.class);
    }

    public Mono<ServerResponse> home(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(Flux.just("App works!!!"), String.class);
    }
}
