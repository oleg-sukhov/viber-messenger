package ua.vn.os.messanger.client;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import ua.vn.os.messanger.client.request.WebHookRequest;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class DefaultViberClient implements ViberClient {
    private static final String AUTH_HEADER_NAME = "X-Viber-Auth-Token";
    private static final String PRIVATE_TOKEN = "46c145f56627d52c-decadff62a933ee5-4f02efc6563c69b4";

    private final RestTemplate viberRestClient;

    public DefaultViberClient(final RestTemplate viberRestClient) {
        this.viberRestClient = viberRestClient;
    }

    @Override
    public Mono<String> sendStartConversationWebHook() {
        final WebHookRequest webHookRequest = createStartConversationWebHookRequest();
        return sendWebHookRequest(webHookRequest);
    }

    @Override
    public Mono<String> sendEndConversationWebHook() {
        final WebHookRequest endConversationWebHookBody = createEndConversationWebHookBody();
        return sendWebHookRequest(endConversationWebHookBody);
    }

    @Override
    public Mono<String> fetchAccountInfo() {
        final RequestEntity<String> requestEntity = RequestEntity
                .post(RequestType.ACCOUNT_INFO.getUri())
                .header(AUTH_HEADER_NAME, PRIVATE_TOKEN)
                .body("{ \"auth_token\": " + "\"" + PRIVATE_TOKEN + "\"}");
        ResponseEntity<String> response = viberRestClient.exchange(requestEntity, String.class);
        return Mono.justOrEmpty(response.getBody());

    }

    @Override
    public Mono<String> sentMessage(final Mono<String> message) {
        final RequestEntity<String> requestEntity = RequestEntity
                .post(RequestType.SEND_MESSAGE.getUri())
                .header(AUTH_HEADER_NAME, PRIVATE_TOKEN)
                .body("{ \"auth_token\": " + "\"" + PRIVATE_TOKEN + "\", \"receiver\": \"V+5X9zYTqfD7LtBpP77G9g==\", \"type\": \"text\", \"text\": \"test\"}");
        ResponseEntity<String> response = viberRestClient.exchange(requestEntity, String.class);
        return Mono.justOrEmpty(response.getBody());
    }

    private Mono<String> sendWebHookRequest(final WebHookRequest webHookRequest) {
        RequestEntity<WebHookRequest> webHookRequestEntity = createWebHookRequestEntity(webHookRequest);
        ResponseEntity<String> response = viberRestClient.exchange(webHookRequestEntity, String.class);
        return Mono.justOrEmpty(response.getBody());
    }

    private RequestEntity<WebHookRequest> createWebHookRequestEntity(final WebHookRequest webHookRequest) {
        return RequestEntity
                .post(RequestType.WEBHOOK.getUri())
                .header(AUTH_HEADER_NAME, PRIVATE_TOKEN)
                .body(webHookRequest);
    }

    private WebHookRequest createStartConversationWebHookRequest() {
        return WebHookRequest.builder()
                .url("https://00aab422.ngrok.io/")
                .eventTypes(createEventTypes())
                .build();
    }

    private WebHookRequest createEndConversationWebHookBody() {
        return WebHookRequest.builder()
                .url("")
                .build();
    }

    private List<String> createEventTypes() {
        return Stream.of(EventType.values())
                .map(EventType::getValue)
                .collect(toList());
    }
}

