package core;

import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.Resource;

/**
 *
 *  Module Name: User Service
 *
 *  Description: The User service defines the generic user object used to hold data representing a User
 *  of the Good Samaritan Application. Chief uses are authentication and data association between Users and Deeds.
 *
 *  Date: 3/21/16
 *
 *
 *  Author: Brandon Yates
 */

@Service
public class User {

//    @NotNull
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String hashedPassword;

    public User () {}

    public User (String id, String firstName, String lastName, String emailAddress, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.hashedPassword = password;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }


    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }


    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmailAddress() { return emailAddress; }

    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }


    public String getHashedPassword() { return hashedPassword; }

    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }

    public String toString() {
        return "Id: " + this.id + "\nFirstName: " + this.firstName + "\nLastName: "
                + this.lastName + "\nEmailAddress: " + this.emailAddress + "\nHashedPassword: " + this.hashedPassword;
    }

    public boolean equals(User candidate) {

        boolean idMatch = (this.id.equals(candidate.id));
        boolean fnMatch = (this.firstName.equals(candidate.firstName));
        boolean lnMatch = (this.lastName.equals(candidate.lastName));
        boolean emailMatch = (this.emailAddress.equals(candidate.emailAddress));
        boolean passwordMatch = (this.hashedPassword.equals(candidate.hashedPassword));

        return (idMatch && fnMatch && lnMatch && emailMatch && passwordMatch);
    }

    public void copy(User assignee) {
        this.id = assignee.id;
        this.firstName = assignee.firstName;
        this.lastName = assignee.lastName;
        this.emailAddress = assignee.emailAddress;
        this.hashedPassword = assignee.hashedPassword;
    }
}
