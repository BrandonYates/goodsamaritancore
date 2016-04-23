package core;

/**
 *
 *  Module Name: Deed Controller
 *
 *  Description: Controls REST operations for creating and manipulating Deeds.
 *
 *  Date: 3/25/16
 *
 *
 *  Author: Joseph DeStefanis
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@RestController
public class DeedController {

  @Autowired
  private DeedRepository deedRepository;

  @Autowired
  private LocationRepository locRepository;

  @RequestMapping(value = "/createDeed/params", method = RequestMethod.POST)
  public String createDeed(@RequestParam("desc")String desc,
                           @RequestParam("date")long date,
                           @RequestParam("uid")String uid,
                           @RequestParam("latitude")Double lat,
                           @RequestParam("longitude")Double lon,
                           @RequestParam("pointvalue")int pointValue) {

    Location l = new Location(UUID.randomUUID().toString(), lat, lon);
    locRepository.save(l);
    Deed newDeed = new Deed(String.valueOf(UUID.randomUUID()), desc, date, uid, l, pointValue);
    deedRepository.save(newDeed);
    return newDeed.getId();
  }

  @RequestMapping(value = "/createDeed/obj", method = RequestMethod.POST)
  public String createDeed(Deed deed) {
    deedRepository.save(deed);
    return deed.getId();
  }

  @RequestMapping(value = "/findDeedById", method = RequestMethod.GET)
  public Deed findById(@RequestParam("id")String id) {
 
        return deedRepository.findById(id);
  }

  @RequestMapping(value = "/findDeedByRequestingUserId", method = RequestMethod.GET)
  public Collection<Deed> findByRequestingUserId(@RequestParam("uid")String requestingUserId) {
        Collection<Deed> deeds = deedRepository.findByRequestingUser(requestingUserId);
        if(deeds == null) {
            System.out.println("Deeds Not Found");
        } else
        {
            System.out.println("Deeds Found");
        }
        return deeds;
        
  }

  @RequestMapping(value = "/updateDeed", method = RequestMethod.POST)
  public Deed update(@RequestParam("id")String id, Deed deed) {

        Deed oldDeed = deedRepository.findById(id);

        if(oldDeed != null) {
            oldDeed.copy(deed);
            deedRepository.save(oldDeed);
            return oldDeed;
        }

        return null;
    }

    @RequestMapping(value = "/deleteDeed", method = RequestMethod.POST)
    public Deed delete(@RequestParam("id")String id) {
        Deed d = deedRepository.findById(id);

        deedRepository.delete(d);

        return d;
    }

    @RequestMapping(value = "/getactivedeeds", method = RequestMethod.GET)
    public Collection<Deed> getActiveDeeds () {
        Collection<Deed> all = deedRepository.findAll();

        Collection<Deed> active = new ArrayList<Deed>();

        for(Deed deed: all) {
            if(deed.isActive()) {
                active.add(deed);
            }
        }
        return active;
    }

}