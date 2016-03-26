package core;

/**
 *
 *  Module Name: User Controller
 *
 *  Description: The User service defines the generic user object used to hold data representing a User
 *  of the Good Samaritan Application. Chief uses are authentication and data association between Users and Deeds.
 *
 *  Date: 3/21/16
 *
 *
 *  Author: Brandon Yates
 */

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;
    import core.UserRepository;
    import core.StringManipulation;

    import javax.annotation.Resource;
    import java.util.Collection;
    import java.util.Iterator;
    import java.util.UUID;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public void createUser(@RequestParam("firstName")String firstName,
                           @RequestParam("lastName")String lastName,
                           @RequestParam("emailAddress")String emailAddress,
                           String password) {
        StringManipulation manipulator = new StringManipulation();
        manipulator.setOriginal(password);
        String readyForStorage = manipulator.getPassword();
        User newUser = new User(String.valueOf(UUID.randomUUID()), firstName, lastName, emailAddress, password);
        userRepository.save(newUser);
    }

    @RequestMapping(value = "/findUserById", method = RequestMethod.GET)
    public User findById(@RequestParam("id")String id) {

        return userRepository.findById(id);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public User update(@RequestParam("id")String id, User user) {

        User oldUser = userRepository.findById(id);

        oldUser.copy(user);

        userRepository.save(oldUser);

        return oldUser;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public User delete(@RequestParam("id")String id) {
        User condemned = userRepository.findById(id);

        userRepository.delete(condemned);

        return condemned;
    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public User authenticateUser(@RequestParam("emailAddress")String emailAddress,
                                    String password) {

        Collection<User> users = userRepository.findByEmailAddress(emailAddress);
        StringManipulation manipulator = new StringManipulation();
        manipulator.setOriginal(password);

        for(Iterator<User> iter = users.iterator(); iter.hasNext();) {
            User user = iter.next();

            String hashed = manipulator.getPassword();

            if(user.getHashedPassword().equals(hashed)) {
                return user;
            }
        }
        return null;
    }
}