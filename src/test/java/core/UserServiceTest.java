package core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

//import core.User;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by brandonyates on 3/26/16.
 */

public class UserServiceTest {

//    @Autowired
//    UserController userController;

//    public void setUserController(UserController userController) {
//        this.userController = userController;
//    }

    @Autowired
    private StringManipulation encoder;

    @Test
    public void testUserModel () {

        System.out.println("################ PART 1 ################");
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/test-context.xml");

        UserController userController = context.getBean(UserController.class);

        //create a few test users

        User testUser = new User(String.valueOf(UUID.randomUUID()), "Brandon", "Yates",
                "brandonyates66@gmail.com", "password");

        System.out.println("################ PART 2 ################");

        //test some basic operations
        User shouldMatch = new User();

        shouldMatch.copy(testUser);

        Assert.assertNotNull(testUser);

        System.out.println(testUser.toString());

        Assert.assertTrue(testUser.equals(shouldMatch));

        User user2 = new User(String.valueOf(UUID.randomUUID()), "Samuel", "Rodriguez",
                "samtheman@gmail.com", "password2");

        userController.createUser(user2);

        Assert.assertFalse(testUser.equals(user2));

        System.out.println("################ PART 3 ################");
        //test REST operations
        Assert.assertNotNull(userController);

        userController.createUser(testUser);

        User found = userController.findById(testUser.getId());

        Assert.assertNotNull(found);

        User authenticated = userController.authenticateUser("brandonyates66@gmail.com", "password");
//
        Assert.assertNotNull(authenticated);

        userController.update(testUser.getId(), testUser);

        testUser = userController.findById(testUser.getId());

        System.out.println(testUser.toString());

        userController.delete(user2.getId());
        userController.delete(testUser.getId());
        userController.delete(authenticated.getId());


        System.out.println("################ PART 4 (finished) ################");
    }
}
