package core;

/**
 * Created by brandonyates on 3/22/16.
 */

    import java.util.concurrent.atomic.AtomicLong;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;
    import java.util.UUID;
//    import UserRepository;

@RestController
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/user")
    public User user(@RequestParam(value="firstName", defaultValue="Brandon")String id,
                     String firstName, String lastName, String emailAddress, String password) {
        return new User(String.valueOf(UUID.randomUUID()),
                String.format(template, firstName), lastName, emailAddress, password);
    }

    @RequestMapping("/user/findById")
    public User findById(@RequestParam(value="id")String id) {
        return new User();
    }
}