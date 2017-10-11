package ua.vn.os.messanger.client.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MessageRequest {
    private String authToken;
    private String receiver;
    private String type;
    private String text;

}
