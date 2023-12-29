package dev.melis.engelsizgonuller.core.user;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserPasswordEncoder {

    String encodePassword(String password);

    PasswordEncoder getEncoder();

    boolean matces(String password, String passwordHash);
}
