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
    private static final String PRIVATE_TOKEN = "";

    private final WebClient webClient;
    private final ObjectMapper jsonMapper;

    public DefaultViberClient(final ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
        this.webClient = WebClient.create(API_HOST);
    }

    @Override
    public Mono<String> sendWebHook() {
        final WebHook webHookRequestBody = createWebHookBody();

        try {
            jsonMapper.writeValue(System.out, webHookRequestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return webClient
                .post()
                .uri(WEB_HOOK_PATH)
                .body(Mono.just("{\"url\":\"\"}"), String.class)
                .header("X-Viber-Auth-Token", PRIVATE_TOKEN)
                .exchange()
                .block()
                .bodyToMono(String.class);

    }

    private WebHook createWebHookBody() {
        return WebHook.builder()
                .url("https://76d58e5c.ngrok.io/")
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
