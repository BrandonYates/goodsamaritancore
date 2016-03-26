package core;

import org.junit.Assert;
import org.junit.Test;

//import core.User;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by brandonyates on 3/26/16.
 */

public class UserServiceTest {

    @Resource
    UserController userController;

    @Test
    public void testUserModel () {

        StringManipulation generator = new StringManipulation();

        String pw = "password";
        generator.setOriginal(pw);
        User testUser = new User(String.valueOf(UUID.randomUUID()), "Brandon", "Yates",
                "brandonyates66@gmail.com", generator.getPassword());

        User shouldMatch = new User();

        shouldMatch.copy(testUser);

        Assert.assertNotNull(testUser);

        System.out.println(testUser.toString());

        Assert.assertTrue(testUser.equals(shouldMatch));

        String pw2 = "password2";
        generator.setOriginal(pw2);
        User user2 = new User(String.valueOf(UUID.randomUUID()), "Samuel", "Rodriguez",
                "samtheman@gmail.com", generator.getPassword());

        Assert.assertFalse(testUser.equals(user2));


    }
}
