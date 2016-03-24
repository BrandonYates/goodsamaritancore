package core;

/**
 * Created by brandonyates on 3/24/16.
 */

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findById (String id);
    List<User> findByFirstName (String firstName);
    List<User> findByLastName (String lastName);
    List<User> findByEmailAddress(String emailAddress);
}
