package core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Created by brandonyates on 3/24/16.
 *
 * Used to salt and hash passwords
 */

public class StringManipulation {
    private String original;
    private String salted;
    private String hashed;


    @Value("${com.security.salt}")
    private String salt;


    public void setOriginal(String password) {
        this.original = password;
        System.out.println("SALT: " + salt);
//        this.salted = password.concat(salt);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.hashed = passwordEncoder.encode(this.original);

    }

    public String getPassword () {return this.hashed; }
}
