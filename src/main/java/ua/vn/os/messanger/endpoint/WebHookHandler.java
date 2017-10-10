package ua.vn.os.messanger.endpoint;

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

    public Mono<ServerResponse> sendStartConversationWebHook(final ServerRequest request) {
        return ServerResponse
                .ok()
                .body(viberClient.sendStartConversationWebHook(), String.class);
    }

    public Mono<ServerResponse> sendEndConversationWebHook(final ServerRequest request) {
        return ServerResponse
                .ok()
                .body(viberClient.sendEndConversationWebHook(), String.class);
    }

    public Mono<ServerResponse> fetchAccountInfo(final ServerRequest request) {
        return ServerResponse
                .ok()
                .body(viberClient.fetchAccountInfo(), String.class);
    }

    public Mono<ServerResponse> sendMessage(final ServerRequest request) {
        return ServerResponse
                .ok()
                .body(viberClient.sentMessage(request.bodyToMono(String.class)), String.class);
    }

    public Mono<ServerResponse> home(ServerRequest request) {
        request.bodyToMono(String.class).subscribe(System.out::println);
        return ServerResponse
                .ok()
                .body(Flux.just("App works!!!"), String.class);
    }
}
