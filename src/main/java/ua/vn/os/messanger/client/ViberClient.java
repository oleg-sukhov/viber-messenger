package ua.vn.os.messanger.client;

import reactor.core.publisher.Mono;

public interface ViberClient {
    Mono<String> sendWebHook();
}
