package core;

/**
 * Created by brandonyates on 3/22/16.
 */

    import java.util.concurrent.atomic.AtomicLong;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;
    import java.util.UUID;

@RestController
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/user")
    public User user(@RequestParam(value="firstName", defaultValue="Brandon")String id,
                     String firstName, String lastName, String emailAddress) {
        return new User(String.valueOf(UUID.randomUUID()),
                String.format(template, firstName), lastName, emailAddress);
    }
}