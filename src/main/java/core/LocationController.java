package core;

/**
 *  LocationController.java
 *
 * Created by Samuel Rodriguez on 3/26/16.
 *
 *  Used for the CRUD operations dealing with the location object
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    // create
    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public void createLocation(@RequestParam("latitude")Double latitude, @RequestParam("longitude")Double longitude) {
        Location newLocation = new Location(String.valueOf(UUID.randomUUID()), latitude, longitude);
        locationRepository.save(newLocation);
    }

    // retrieve
    @RequestMapping(value = "/location/findById", method = RequestMethod.GET)
    public Location findById(@RequestParam(value="id")String id) {

        return locationRepository.findById(id);
    }
    // update
    @RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
    public Location update(@RequestParam("id")String id, Location location) {

        Location oldLocation = locationRepository.findById(id);
        oldLocation.copy(location);
        locationRepository.save(oldLocation);
        return oldLocation;
    }
    // delete
    @RequestMapping(value = "/deleteLocation", method = RequestMethod.POST)
    public Location delete(@RequestParam("id")String id) {

        Location delLocation = locationRepository.findById(id);
        locationRepository.delete(delLocation);
        return delLocation;
    }
}
