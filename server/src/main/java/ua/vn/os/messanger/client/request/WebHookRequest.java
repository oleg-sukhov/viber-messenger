package ua.vn.os.messanger.client.request;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class WebHookRequest {
    private String url;
    private List<String> eventTypes;
}
