package core;

/**
 *  LocationRepository.java
 *
 * Created by Samuel Rodriguez on 3/26/16.
 *
 *  Used to let the location interact with the Mongo
 */

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {

    Location findById (String id); // find a location by mongo id

}
