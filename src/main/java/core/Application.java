package core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.System;

@SpringBootApplication
public class Application implements CommandLineRunner {



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/test-context.xml");

        System.out.println("RUN");

        /**
         * Think hard about putting things here. This service exists only to answer calls posted by the Android application.
         * It has no "active" functionality.  The print lets us know that the application is currently running. Unless more
         * start up tasks become necessary this should be empty
         *
         * tl;dr This method is only for essential intialization tasks
         */
    }

}