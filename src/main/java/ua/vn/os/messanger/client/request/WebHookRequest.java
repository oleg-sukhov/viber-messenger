package ua.vn.os.messanger.client.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WebHookRequest {
    private String url;
    private List<String> eventTypes;
}
