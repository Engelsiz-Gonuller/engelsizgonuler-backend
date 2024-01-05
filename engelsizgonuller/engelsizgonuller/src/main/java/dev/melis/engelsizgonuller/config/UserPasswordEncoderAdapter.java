package dev.melis.engelsizgonuller.config;

import dev.melis.engelsizgonuller.services.user.UserPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordEncoderAdapter implements UserPasswordEncoder {

    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public PasswordEncoder getEncoder() {
        return passwordEncoder;
    }

    @Override
    public boolean matces(String password, String passwordHash) {
        return passwordEncoder.matches(password,passwordHash);
    }
}
