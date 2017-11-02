package ua.vn.os.messanger.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetailsRepository;
import org.springframework.stereotype.Repository;
import ua.vn.os.messanger.domain.User;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String>, UserDetailsRepository {
}
