package ua.vn.os.messanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.WebApplicationType.REACTIVE;

@SpringBootApplication
public class MessengerApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MessengerApplication.class);
        app.setWebApplicationType(REACTIVE);
        app.run(args);
    }
}
