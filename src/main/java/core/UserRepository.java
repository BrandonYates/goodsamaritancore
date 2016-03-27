package core;

/**
 * Created by brandonyates on 3/24/16.
 */

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findById (String id);
    Collection<User> findByFirstName (String firstName);
    Collection<User> findByLastName (String lastName);
    Collection<User> findByEmailAddress(String emailAddress);

}
