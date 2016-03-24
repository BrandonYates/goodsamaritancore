package core;

/**
 * Created by brandonyates on 3/21/16.
 */

//import static org.springframework.util.Assert.notNull;

/**
 *  The User service defines the generic user object used to hold data representing a User
 *  of the Good Samaritan Application.
 */
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

    public boolean equals(User candidate) {

        boolean idMatch = (this.id.equals(candidate.id));
        boolean fnMatch = (this.firstName.equals(candidate.firstName));
        boolean lnMatch = (this.lastName.equals(candidate.lastName));
        boolean emailMatch = (this.emailAddress.equals(candidate.emailAddress));
        boolean passwordMatch = (this.hashedPassword.equals(candidate.hashedPassword));

        return (idMatch && fnMatch && lnMatch && emailMatch && passwordMatch);
    }
}
