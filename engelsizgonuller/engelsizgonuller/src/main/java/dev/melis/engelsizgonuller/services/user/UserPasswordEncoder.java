package dev.melis.engelsizgonuller.services.user;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserPasswordEncoder {

    String encode(String password);

    PasswordEncoder getEncoder();

    boolean matches(String password, String passwordHash);
}
