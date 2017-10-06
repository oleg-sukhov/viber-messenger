package ua.vn.os.messanger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import ua.vn.os.messanger.endpoint.WebHookHandler;

import javax.validation.constraints.NotNull;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MessengerConfiguration {

    @Bean
    public RouterFunction<?> webHookRoute(@NotNull final WebHookHandler webHookHandler) {
        return route(GET("/conversation/start"), webHookHandler::sendWebHook);
    }
}
