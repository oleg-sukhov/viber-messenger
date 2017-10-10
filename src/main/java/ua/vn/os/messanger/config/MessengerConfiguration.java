package ua.vn.os.messanger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.server.RouterFunction;
import ua.vn.os.messanger.endpoint.WebHookHandler;

import javax.validation.constraints.NotNull;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MessengerConfiguration {

    @Bean
    public RouterFunction<?> webHookRoute(@NotNull final WebHookHandler webHookHandler) {
        return route(GET("/conversation/start"), webHookHandler::sendStartConversationWebHook)
                .andRoute(GET("/conversation/end"), webHookHandler::sendEndConversationWebHook)
                .andRoute(POST("/"), webHookHandler::home);
    }

    @Bean
    public RestTemplate viberRestClient() {
        return new RestTemplate();
    }
}
