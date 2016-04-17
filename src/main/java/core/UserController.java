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
    import org.springframework.http.MediaType;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;
    import com.google.gson.Gson;

    import javax.ws.rs.Consumes;
    import javax.ws.rs.DELETE;
    import javax.ws.rs.GET;
    import javax.ws.rs.POST;
    import javax.ws.rs.PUT;
    import javax.ws.rs.Path;
    import javax.ws.rs.PathParam;
    import javax.ws.rs.Produces;
    import javax.ws.rs.QueryParam;
    import javax.ws.rs.core.Response;

    import java.util.Collection;
    import java.util.Iterator;
    import java.util.UUID;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired(required = false)
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    //standard CRUD operations

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String printHello () {
        String hello = "HELLO";
        return hello;
    }

    @Path("/createUser/params")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @RequestMapping(value = "/createUser/params",  method = RequestMethod.POST)
    public Response createUser(@RequestParam("firstName")String firstName,
                               @RequestParam("lastName")String lastName,
                           @RequestParam("emailAddress")String emailAddress,
                           String password) {
        System.out.println("CREATE USER CALLED");
        User newUser = new User(String.valueOf(UUID.randomUUID()), firstName, lastName, emailAddress, password);
        userRepository.save(newUser);

        Response.ResponseBuilder response = Response.ok(newUser);
        return response.build();
    }

    @Path("/createUser/")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public User createUser(User user) {

        userRepository.save(user);

        return user;
    }

    @RequestMapping(value = "/findUserById", method = RequestMethod.GET)
    public User findById(@RequestParam("id")String id) {

        return userRepository.findById(id);
    }

    @RequestMapping(value = "/findUserByEmail", method = RequestMethod.GET)
    public Collection<User> findByEmail(@RequestParam("emailAddress") String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public User update(@RequestParam("id")String id, User user) {

        User oldUser = userRepository.findById(id);

        if(oldUser != null) {
            oldUser.copy(user);
            userRepository.save(oldUser);
            return oldUser;
        }

        return null;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public User delete(@RequestParam("id")String id) {
        User condemned = userRepository.findById(id);

        userRepository.delete(condemned);

        return condemned;
    }

    //this method is likely to evolve significantly as a more robust authentication method is employed from android
    @Path("/authenticate")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public Response authenticateUser(@RequestParam("emailAddress")String emailAddress,
                                    String password) {

        Collection<User> users = userRepository.findByEmailAddress(emailAddress);

        System.out.println("result: " + encoder.matches(password, encoder.encode(password)));

        for(Iterator<User> iter = users.iterator(); iter.hasNext();) {
            User user = iter.next();

            System.out.println("****************");
            System.out.println(user.toString());
            System.out.println("password: " + password);
            System.out.println("****************");
            System.out.println("matches " + encoder.matches(password, user.getHashedPassword()));
            System.out.println("****************");

            if(encoder.matches(password, user.getHashedPassword())) {
                Gson gson = new Gson();
                System.out.println(gson.toJson(user));
                Response.ResponseBuilder response = Response.ok(user);
                return response.build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}