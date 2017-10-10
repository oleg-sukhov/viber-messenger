package ua.vn.os.messanger.client;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class DefaultViberClient implements ViberClient {

    private static final String API_HOST = "https://chatapi.viber.com";
    private static final String WEB_HOOK_PATH = "/pa/set_webhook";
    private static final String AUTH_HEADER_NAME = "X-Viber-Auth-Token";
    private static final String PRIVATE_TOKEN = "46bb903684a7d498-70e6f9c4e80f09bc-742d19535a7d42f9";

    private final RestTemplate viberRestClient;

    public DefaultViberClient(final RestTemplate viberRestClient) {
        this.viberRestClient = viberRestClient;
    }

    @Override
    public Mono<String> sendStartConversationWebHook() {
        final WebHook startConversationWebHookBody = createStartConversationWebHookBody();
        return sendWebHookRequest(startConversationWebHookBody);
    }

    @Override
    public Mono<String> sendEndConversationWebHook() {
        final WebHook endConversationWebHookBody = createEndConversationWebHookBody();
        return sendWebHookRequest(endConversationWebHookBody);
    }

    private Mono<String> sendWebHookRequest(final WebHook webHookRequestBody) {
        RequestEntity<WebHook> request = createWebHookRequestEntity(webHookRequestBody);
        ResponseEntity<String> response = viberRestClient.exchange(request, String.class);
        return Mono.justOrEmpty(response.getBody());
    }

    private RequestEntity<WebHook> createWebHookRequestEntity(WebHook webHookRequestBody) {
        try {
            return RequestEntity
                    .post(new URI(API_HOST + WEB_HOOK_PATH))
                    .header(AUTH_HEADER_NAME, PRIVATE_TOKEN)
                    .body(webHookRequestBody);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    private WebHook createStartConversationWebHookBody() {
        return WebHook.builder()
                .url("https://44ab1e9f.ngrok.io")
                .event_types(emptyList())
                .build();
    }

    private WebHook createEndConversationWebHookBody() {
        return WebHook.builder()
                .url("")
                .build();
    }
}

@Data
@Builder
class WebHook {
    private String url;
    private List<String> event_types;
}

