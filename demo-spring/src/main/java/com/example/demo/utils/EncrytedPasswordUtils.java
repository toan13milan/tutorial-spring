package com.example.demo.utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class EncrytedPasswordUtils {
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
 
}
