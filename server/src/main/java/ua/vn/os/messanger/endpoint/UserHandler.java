package ua.vn.os.messanger.endpoint;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ua.vn.os.messanger.domain.Contact;
import ua.vn.os.messanger.service.UserService;

@Component
public class UserHandler {
    private static final String USER_ID_PATH_VARIABLE = "id";
    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> contacts(ServerRequest request) {
        String userId = request.pathVariable(USER_ID_PATH_VARIABLE);
        return ServerResponse
                .ok()
                .body(userService.findUserContacts(userId), Contact.class);
    }
}
