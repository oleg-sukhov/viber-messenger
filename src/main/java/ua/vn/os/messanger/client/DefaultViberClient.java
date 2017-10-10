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
    private static final String ACCOUNT_INFO_PATH = "/pa/get_account_info";
    private static final String SEND_MESSAGE_PATH = "/pa/sent_message";

    private static final String AUTH_HEADER_NAME = "X-Viber-Auth-Token";
    private static final String PRIVATE_TOKEN = "46c145f56627d52c-decadff62a933ee5-4f02efc6563c69b4";

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

    @Override
    public Mono<String> fetchAccountInfo() {
        try {
            final RequestEntity<String> requestEntity = RequestEntity
                    .post(new URI(API_HOST + ACCOUNT_INFO_PATH))
                    .header("auth_token", PRIVATE_TOKEN)
                    .body("{ \"auth_token\": " + "\"" + PRIVATE_TOKEN + "\"}");
            ResponseEntity<String> response = viberRestClient.exchange(requestEntity, String.class);
            return Mono.justOrEmpty(response.getBody());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public Mono<String> sentMessage() {
        try {
            final RequestEntity<String> requestEntity = RequestEntity
                    .post(new URI(API_HOST + SEND_MESSAGE_PATH))
                    .header("auth_token", PRIVATE_TOKEN)
                    .body("{ \"auth_token\": " + "\"" + PRIVATE_TOKEN + "\", \"receiver\": \"eNFFlSxyJH3fkDce5WWEuQ==\", \"type\": \"text\", \"text\": \"test\"}");
            ResponseEntity<String> response = viberRestClient.exchange(requestEntity, String.class);
            return Mono.justOrEmpty(response.getBody());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
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

