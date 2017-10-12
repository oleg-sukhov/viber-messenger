package ua.vn.os.messanger.client;

import lombok.Getter;

import java.net.URI;
import java.net.URISyntaxException;

@Getter
public enum RequestType {
    WEBHOOK("/pa/set_webhook"),
    ACCOUNT_INFO("/pa/get_account_info"),
    SEND_MESSAGE("/pa/send_message");

    private final URI uri;

    RequestType(final String path) {
        try {
            this.uri = new URI("https://chatapi.viber.com" + path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
