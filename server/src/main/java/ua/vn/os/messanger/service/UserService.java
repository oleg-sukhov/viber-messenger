package ua.vn.os.messanger.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import reactor.core.publisher.Flux;
import ua.vn.os.messanger.domain.Contact;

public interface UserService extends UserDetailsService {
    Flux<Contact> findUserContacts(String userId);
}
