package ua.vn.os.messanger.client;

import reactor.core.publisher.Mono;

public interface ViberClient {
    Mono<String> sendStartConversationWebHook();

    Mono<String> sendEndConversationWebHook();

    Mono<String> fetchAccountInfo();

    Mono<String> sentMessage(final Mono<String> message);
}
