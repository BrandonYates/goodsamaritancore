package core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 *  LocationServiceTest
 *
 * Created by Samuel Rodriguez on 3/26/16.
 *
 *  Test for location creation, retrieve, delete
 *
 */
public class LocationServiceTest {

    @Test
    public void testLocationModel() {

        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/test-context.xml");
        LocationController locationController = context.getBean(LocationController.class);

        System.out.println("Create test");

        Location testLocation = new Location(String.valueOf(UUID.randomUUID()), 1.1, 2.2);

        System.out.println("Retrieve test");

        Location found = locationController.findById(testLocation.getId());

        Assert.assertNotNull(found);

        System.out.println("Delete test");
        locationController.delete(testLocation.getId());

    }
}
