package dev.melis.engelsizgonuller.services.user;

import dev.melis.engelsizgonuller.services.model.user.User;
import dev.melis.engelsizgonuller.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional=userRepository.findByUserEmail(email);
        return userOptional.orElseThrow(EntityNotFoundException::new);
    }
}
