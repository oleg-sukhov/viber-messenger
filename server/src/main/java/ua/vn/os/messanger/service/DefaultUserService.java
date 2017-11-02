package ua.vn.os.messanger.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ua.vn.os.messanger.domain.Contact;
import ua.vn.os.messanger.domain.User;
import ua.vn.os.messanger.repository.UserRepository;

@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Flux<Contact> findUserContacts(String userId) {
        return userRepository.findById(userId)
                .map(User::getContacts)
                .flatMapMany(Flux::fromIterable);
    }
}
