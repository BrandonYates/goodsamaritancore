package core;

/**
 * Created by brandonyates on 3/22/16.
 */

    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;
    import core.UserRepository;

    import java.util.UUID;


@RestController
public class UserController {


    UserRepository userRepository;

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

    @RequestMapping(value = "/findUserById", method = RequestMethod.POST)
    public User findById(@RequestParam("id")String id) {
        return new User();
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public Boolean authenticateUser(@RequestParam("emailAddress")String emailAddress,
                                    String password) {

        return false;
    }
}