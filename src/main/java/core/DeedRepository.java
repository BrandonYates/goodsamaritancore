package core;

/**
 * Created by Joseph DeStefanis on 3/26/16.
 */

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
//
@Repository
public interface DeedRepository extends MongoRepository<Deed, String> {

    Deed findById (String id);
    Collection<Deed> findByRequestingUserId (String requestingUserId);
}