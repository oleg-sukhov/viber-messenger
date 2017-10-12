package ua.vn.os.messanger.client;

import lombok.Getter;

@Getter
public enum EventType {
    SUBSCRIBED("subscribed"),
    UNSUBSCRIBED("unsubscribed"),
    WEBHOOK("webhook"),
    CONVERSATION_STARTED("conversation_started"),
    DELIVERED("delivered"),
    FAILED("failed"),
    MESSAGE("message"),
    SEEN("seen");

    private final String value;

    EventType(final String value) {
        this.value = value;
    }
}
