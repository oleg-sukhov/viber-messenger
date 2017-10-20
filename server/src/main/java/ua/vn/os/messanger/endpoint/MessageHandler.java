package ua.vn.os.messanger.endpoint;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ua.vn.os.messanger.client.ViberClient;

@Component
public class MessageHandler {

    private final ViberClient viberClient;

    public MessageHandler(ViberClient viberClient) {
        this.viberClient = viberClient;
    }

    public Mono<ServerResponse> sendMessage(ServerRequest request) {
        return ServerResponse
                .ok()
                .body(viberClient.sentMessage(request.bodyToMono(String.class)), String.class);
    }
}
