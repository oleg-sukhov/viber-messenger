package ua.vn.os.messanger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.server.RouterFunction;
import ua.vn.os.messanger.endpoint.HomeHandler;
import ua.vn.os.messanger.endpoint.MessageHandler;
import ua.vn.os.messanger.endpoint.UserHandler;
import ua.vn.os.messanger.endpoint.WebHookHandler;

import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MessengerConfiguration {

    @Bean
    public RouterFunction<?> webHookRoute(@NotNull WebHookHandler webHookHandler,
                                          @NotNull HomeHandler homeHandler,
                                          @NotNull MessageHandler messageHandler,
                                          @NotNull UserHandler userHandler) {
        return route(GET("/conversation/start"), webHookHandler::sendStartConversationWebHook)
                .andRoute(GET("/conversation/end"), webHookHandler::sendEndConversationWebHook)
                .andRoute(GET("/account"), webHookHandler::fetchAccountInfo)
                .andRoute(POST("/messages/send").and(accept(APPLICATION_JSON)), messageHandler::sendMessage)
                .andRoute(GET("/users/{id}/contacts").and(accept(APPLICATION_JSON)), userHandler::contacts)
                .andRoute(POST("/"), homeHandler::home);
    }

    @Bean
    public RestTemplate viberRestClient() {
        return new RestTemplate();
    }
}
