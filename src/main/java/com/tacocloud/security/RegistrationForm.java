package com.tacocloud.security;

import com.tacocloud.models.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder encoder){
        return new User(username,
                encoder.encode(password),
                fullname, street, city, zip, phone);
    }
}
