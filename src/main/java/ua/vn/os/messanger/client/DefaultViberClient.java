package ua.vn.os.messanger.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class DefaultViberClient implements ViberClient {

    private static final String API_HOST = "https://chatapi.viber.com";
    private static final String WEB_HOOK_PATH = "/pa/set_webhook";
    private static final String PRIVATE_TOKEN = "46bb903684a7d498-70e6f9c4e80f09bc-742d19535a7d42f9";

    private final WebClient webClient;
    private final ObjectMapper jsonMapper;

    public DefaultViberClient(final ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
        this.webClient = WebClient.create(API_HOST);
    }

    @Override
    public Mono<String> sendWebHook() {
        final WebHook webHookRequestBody = createWebHookBody();
        final String body = convertAsJsonString(webHookRequestBody);
        return webClient
                .post()
                .uri(WEB_HOOK_PATH)
                .body(Mono.just(body), String.class)
                .header("X-Viber-Auth-Token", PRIVATE_TOKEN)
                .exchange()
                .block()
                .bodyToMono(String.class);
    }

    private String convertAsJsonString(WebHook webHookRequestBody) {
        try {
            return jsonMapper.writeValueAsString(webHookRequestBody);
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    private WebHook createWebHookBody() {
        return WebHook.builder()
                .url("https://9444e86a.ngrok.io")
                .event_types(emptyList())
                .build();
    }

    @Data
    @Builder
    private static class WebHook {
        private String url;
        private List<String> event_types;
    }
}
