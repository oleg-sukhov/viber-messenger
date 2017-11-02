package ua.vn.os.messanger.service;

import reactor.core.publisher.Flux;
import ua.vn.os.messanger.domain.Contact;

public interface UserService {
    Flux<Contact> findUserContacts(String userId);
}
