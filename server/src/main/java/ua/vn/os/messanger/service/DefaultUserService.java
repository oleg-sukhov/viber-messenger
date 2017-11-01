package ua.vn.os.messanger.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ua.vn.os.messanger.domain.Contact;

@Service
public class DefaultUserService implements UserService {
    private final UserDetailsRepository userDetailsRepository;

    public DefaultUserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO: fix reactive
        return userDetailsRepository.findByUsername(username).block();
    }

    @Override
    public Flux<Contact> findUserContacts(String userId) {
        return Flux.just(
                new Contact("123", "Vasia", "Pupkin")
        );
    }
}
