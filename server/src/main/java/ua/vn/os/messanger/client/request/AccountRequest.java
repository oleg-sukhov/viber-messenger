package ua.vn.os.messanger.client.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountRequest {
    private String authToken;
}
