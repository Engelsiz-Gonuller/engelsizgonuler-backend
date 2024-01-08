package dev.melis.engelsizgonuller.controller.session;

import dev.melis.engelsizgonuller.config.UserSession;
import dev.melis.engelsizgonuller.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class SessionController {

    private final UserRepository userRepository;

    public SessionController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping
    ResponseEntity<?> getSession(UserSession userSession){
        return new ResponseEntity<>(new SessionDeatils(userSession.id(), userSession.username(), userSession.role()), HttpStatus.OK);
    }
}
